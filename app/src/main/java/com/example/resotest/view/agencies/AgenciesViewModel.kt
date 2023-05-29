package com.example.resotest.view.agencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.resotest.model.Agencies
import com.example.resotest.repository.AgenciesRepository
import kotlinx.coroutines.launch

class AgenciesViewModel(
    private val agenciesRepository: AgenciesRepository
) : ViewModel() {
    private val _myAgencies = MutableLiveData<List<Agencies>>()
    val myAgencies: LiveData<List<Agencies>> = _myAgencies

    fun getAllAgencies() {
        viewModelScope.launch {
            _myAgencies.postValue(agenciesRepository.getAllAgencies())
        }
    }

    fun getAgenciesById(nodeId: String) {
        viewModelScope.launch {
            _myAgencies.postValue(agenciesRepository.getAgenciesById(nodeId))
        }
    }
}

class ViewModelFactory(
    private val agenciesRepository: AgenciesRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AgenciesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AgenciesViewModel(agenciesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}