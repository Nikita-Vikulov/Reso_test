package com.example.resotest.view.subject

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import com.example.resotest.R
import com.example.resotest.databinding.FragmentSubjectBinding
import com.example.resotest.view.BaseFragment

class SubjectFragment : BaseFragment<FragmentSubjectBinding>() {

    override fun getViewBinding(container: ViewGroup?): FragmentSubjectBinding =
        FragmentSubjectBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init(){
        val firstLine = "Регион"
        val secondLine = "Подзаголовок"
        val buttonText = "$firstLine\n$secondLine"
        binding.buttonSubject.text = buttonText

        binding.buttonSubject.setOnClickListener {
            val inflater = LayoutInflater.from(context)
            val popupView = inflater.inflate(R.layout.popup_subject, null)

            val popupWindow = PopupWindow(
                popupView,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true
            )
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
        }
    }
}