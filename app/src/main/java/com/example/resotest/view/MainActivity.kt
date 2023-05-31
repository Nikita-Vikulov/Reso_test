package com.example.resotest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.resotest.AGENCIES
import com.example.resotest.R
import com.example.resotest.databinding.ActivityMainBinding
import com.example.resotest.model.Agencies

class MainActivity : AppCompatActivity(), INavigation {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host)
    }

    override fun openDetailsFragment(agencies: Agencies) {
        navController.navigate(
            R.id.action_agenciesFragment_to_detailsFragment,
            bundleOf(AGENCIES to agencies)
        )
    }

    override fun openSubjectFragment() {
        navController.navigate(
            R.id.action_agenciesFragment_to_subjectFragment,
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}