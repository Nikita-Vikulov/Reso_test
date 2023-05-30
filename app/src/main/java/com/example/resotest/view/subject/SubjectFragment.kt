package com.example.resotest.view.subject

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resotest.App
import com.example.resotest.MySharedPreferences
import com.example.resotest.R
import com.example.resotest.databinding.FragmentSubjectBinding
import com.example.resotest.view.BaseFragment

class SubjectFragment : BaseFragment<FragmentSubjectBinding>() {

    private lateinit var mySharedPreferences: MySharedPreferences

    private val subjectViewModel: SubjectViewModel by viewModels {
        ViewModelFactory(
            (requireActivity().application as App).subjectRepository
        )
    }

    override fun getViewBinding(container: ViewGroup?): FragmentSubjectBinding =
        FragmentSubjectBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySharedPreferences = MySharedPreferences(requireContext())
        init()
    }

    private fun init() {
        val firstLine = "Регион"
        var secondLine = "Выберите регион"
        if(mySharedPreferences.getSubject()?.name.toString() != "null"){
            secondLine = mySharedPreferences.getSubject()?.name.toString()
        }
        var buttonText = "$firstLine\n$secondLine"
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

            val recyclerView: RecyclerView = popupView.findViewById(R.id.recycler_view_subject)
            val adapter = SubjectFragmentAdapter { subject ->
                mySharedPreferences.saveSubject(subject)
                secondLine = mySharedPreferences.getSubject()?.name.toString()
                buttonText = "$firstLine\n$secondLine"
                binding.buttonSubject.text = buttonText
                popupWindow.dismiss()
            }

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)

            val searchView: SearchView = popupView.findViewById(R.id.search_view)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subjectViewModel.getSubjectByName(query.toString())
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    subjectViewModel.getSubjectByName(newText.toString())
                    return true
                }
            })
            subjectViewModel.getAllSubject()
            subjectViewModel.mySubject.observe(viewLifecycleOwner) { subject ->
                subject?.let { adapter.setList(it) }
            }

            popupWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL or Gravity.TOP, 0, 0)
        }
    }

}

