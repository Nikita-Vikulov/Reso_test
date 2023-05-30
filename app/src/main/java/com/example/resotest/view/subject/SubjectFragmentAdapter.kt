package com.example.resotest.view.subject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resotest.databinding.ItemSubjectBinding
import com.example.resotest.model.Subject


class SubjectFragmentAdapter(
    private val itemClickListener: (Subject) -> Unit
) : RecyclerView.Adapter<SubjectFragmentAdapter.SubjectViewHolder>() {

    private var listSubject = emptyList<Subject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val binding = ItemSubjectBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(listSubject[position])
    }

    override fun getItemCount(): Int {
        return listSubject.size
    }

    fun setList(list: List<Subject>) {
        listSubject = list
        notifyDataSetChanged()
    }

    inner class SubjectViewHolder(private val binding: ItemSubjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.invoke(listSubject[position])
                }
            }
        }

        fun bind(subject: Subject) {
            binding.tvSubject.text = subject.name
        }
    }
}