package com.example.resotest.view

import com.example.resotest.model.Agencies


interface INavigation {
    fun openDetailsFragment(agencies: Agencies)
    fun openSubjectFragment()
}