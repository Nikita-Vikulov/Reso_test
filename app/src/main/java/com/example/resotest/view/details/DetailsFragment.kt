package com.example.resotest.view.details

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.example.resotest.AGENCIES
import com.example.resotest.App
import com.example.resotest.databinding.FragmentDetailsBinding
import com.example.resotest.model.Agencies
import com.example.resotest.view.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private lateinit var currentAgencies: Agencies

    override fun getViewBinding(container: ViewGroup?): FragmentDetailsBinding =
        FragmentDetailsBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        currentAgencies = arguments?.getParcelable<Agencies>(AGENCIES)
            ?: throw IllegalStateException("Agencies argument is missing")
        init()
    }

    private fun init() {
        with(binding) {
            tvAgency.text = currentAgencies.shortName
            tvAddress.text = currentAgencies.address
            tvPhone.text = currentAgencies.phone
            tvWorkingTimeValue.text = currentAgencies.workSchedule
        }
    }
}