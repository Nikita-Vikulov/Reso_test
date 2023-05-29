package com.example.resotest.view.agencies

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resotest.App
import com.example.resotest.R
import com.example.resotest.databinding.FragmentAgenciesBinding
import com.example.resotest.model.Agencies
import com.example.resotest.view.BaseFragment
import com.example.resotest.view.IAgenciesClickListener
import com.example.resotest.view.INavigation

class AgenciesFragment : BaseFragment<FragmentAgenciesBinding>(), IAgenciesClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listener: INavigation
    private val adapter by lazy { AgenciesFragmentAdapter(this) }

    private val agenciesViewModel: AgenciesViewModel by viewModels {
        ViewModelFactory(
            (requireActivity().application as App).agenciesRepository
        )
    }

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
        //listener = (requireActivity() as? INavigation)!!
        setupMenu()
        init()
    }

    private fun init() {
        recyclerView = binding.recyclerViewAgencies
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        agenciesViewModel.getAllAgencies()
        agenciesViewModel.myAgencies.observe(viewLifecycleOwner) { agencies ->
            agencies.let { adapter.setList(it) }
        }
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
}