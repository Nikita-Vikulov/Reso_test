package com.example.resotest.view.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resotest.model.Subject
import com.example.resotest.repository.SubjectRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SubjectViewModel @Inject constructor(
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

