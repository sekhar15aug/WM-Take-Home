package com.wm.takehome.ui.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wm.takehome.domain.models.Country

import com.wm.takehome.ui.databinding.FragmentListItemBinding

class ListViewAdapter : ListAdapter<(Country), RecyclerView.ViewHolder>(ListDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val country = getItem(position) as Country
                holder.binding(country)
            }
        }
    }

    inner class ViewHolder(private val binding: FragmentListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(country: Country) {
            binding.nameRegion.text = country.name.plus(", ").plus(country.region)
            binding.code.text = country.code
            binding.capital.text = country.capital
        }
    }

    class ListDiffUtils : DiffUtil.ItemCallback<Country>() {
        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }
    }
}