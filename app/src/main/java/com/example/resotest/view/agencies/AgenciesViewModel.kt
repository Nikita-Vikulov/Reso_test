package com.example.resotest.view.agencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resotest.model.Agencies
import com.example.resotest.repository.AgenciesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AgenciesViewModel @Inject constructor(
    private val agenciesRepository: AgenciesRepository
) : ViewModel() {
    private val _myAgencies = MutableLiveData<List<Agencies>>()
    val myAgencies: LiveData<List<Agencies>> = _myAgencies

    fun getAllAgencies() {
        viewModelScope.launch {
            _myAgencies.postValue(agenciesRepository.getAllAgencies())
        }
    }

    fun getAgenciesById(nodeId: Int) {
        viewModelScope.launch {
            _myAgencies.postValue(agenciesRepository.getAgenciesById(nodeId))
        }
    }
}