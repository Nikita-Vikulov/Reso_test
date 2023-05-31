package com.example.resotest.view.agencies

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resotest.App
import com.example.resotest.MySharedPreferences
import com.example.resotest.R
import com.example.resotest.databinding.FragmentAgenciesBinding
import com.example.resotest.model.Agencies
import com.example.resotest.view.BaseFragment
import com.example.resotest.view.IAgenciesClickListener
import com.example.resotest.view.INavigation
import com.example.resotest.view.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class AgenciesFragment : BaseFragment<FragmentAgenciesBinding>(), IAgenciesClickListener {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory
    private val agenciesViewModel: AgenciesViewModel by lazy {
        ViewModelProvider(this, viewModeFactory)[AgenciesViewModel::class.java]
    }

    private lateinit var mySharedPreferences: MySharedPreferences
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var recyclerView: RecyclerView
    private lateinit var listener: INavigation
    private val adapter by lazy { AgenciesFragmentAdapter(this) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is INavigation) {
            listener = context
        }
    }

    override fun getViewBinding(container: ViewGroup?): FragmentAgenciesBinding =
        FragmentAgenciesBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        mySharedPreferences = MySharedPreferences(requireContext())
        setupMenu()
        init()
    }

    private fun init() {
        recyclerView = binding.recyclerViewAgencies
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        val subjectNumber = mySharedPreferences.getSubject()?.nodeId?.toInt()
        if (subjectNumber == null) {
            agenciesViewModel.getAllAgencies()
        } else {
            agenciesViewModel.getAgenciesById(subjectNumber)
        }
        agenciesViewModel.myAgencies.observe(viewLifecycleOwner) { agencies ->
            val sortedAgencies = sortAgenciesByDistance(agencies)
            adapter.setList(sortedAgencies)
        }
    }

    private fun sortAgenciesByDistance(agencies: List<Agencies>): List<Agencies> {
        val currentLocation = requestLocation()

        return agencies.sortedBy { agency ->
            val agencyLocation = Location("agency")
            agencyLocation.latitude = agency.latitude
            agencyLocation.longitude = agency.longitude
            currentLocation.distanceTo(agencyLocation)
        }
    }

    private fun requestLocation(): Location {
        val currentLocation = Location("location")
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    currentLocation.latitude = location.latitude
                    currentLocation.longitude = location.longitude
                }
            }
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
        return currentLocation
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.item_subject -> {
                        listener.openSubjectFragment()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onItemClick(agencies: Agencies) {
        listener.openDetailsFragment(agencies)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
}
