package com.example.resotest.view.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.resotest.model.Subject
import com.example.resotest.repository.SubjectRepository
import kotlinx.coroutines.launch

class SubjectViewModel(
    private val subjectRepository: SubjectRepository
) : ViewModel() {
    private val _mySubject = MutableLiveData<List<Subject>>()
    val mySubject: LiveData<List<Subject>> = _mySubject

    fun getAllSubject() {
        viewModelScope.launch {
            _mySubject.postValue(subjectRepository.getAllSubject())
        }
    }

    fun getSubjectByName(nodeId: String) {
        viewModelScope.launch {
            _mySubject.postValue(subjectRepository.getSubjectByName(nodeId))
        }
    }
}

class ViewModelFactory(
    private val subjectRepository: SubjectRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SubjectViewModel(subjectRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
