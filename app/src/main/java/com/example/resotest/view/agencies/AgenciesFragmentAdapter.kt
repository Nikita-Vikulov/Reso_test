package com.example.resotest.view.agencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.resotest.databinding.ItemAgenciesBinding
import com.example.resotest.model.Agencies
import com.example.resotest.view.IAgenciesClickListener

class AgenciesFragmentAdapter(private val listener: IAgenciesClickListener) :
    ListAdapter<Agencies, AgenciesFragmentAdapter.AgenciesViewHolder>(AGENCIES_COMPARATOR) {

    private var listAgencies = emptyList<Agencies>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AgenciesViewHolder {
        val binding = ItemAgenciesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AgenciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgenciesViewHolder, position: Int) {
        holder.bind(listAgencies[position])
    }

    inner class AgenciesViewHolder(private val binding: ItemAgenciesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(agencies: Agencies) {
            with(binding) {
                tvAgency.text = agencies.shortName
                tvAddress.text = agencies.shortAddress
            }
        }
    }

    override fun getItemCount(): Int {
        return listAgencies.size
    }

    fun setList(list: List<Agencies>) {
        listAgencies = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: AgenciesViewHolder) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(listAgencies[holder.bindingAdapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: AgenciesViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

    companion object {
        private val AGENCIES_COMPARATOR = object : DiffUtil.ItemCallback<Agencies>() {
            override fun areItemsTheSame(oldItem: Agencies, newItem: Agencies): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Agencies, newItem: Agencies): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
