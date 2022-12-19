package com.example.kotlinnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnavigation.Models.Arts
import com.example.kotlinnavigation.databinding.RecyclerRowBinding

class ArtsAdapter(val artsList :List<Arts>) : RecyclerView.Adapter<ArtsAdapter.ArtsViewHolder>() {
    class ArtsViewHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtsViewHolder {
        var binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtsViewHolder, position: Int) {
        holder.binding.rowName.text=artsList.get(position).name
        holder.binding.rowDescription.text=artsList.get(position).description
        holder.binding.rowDate.text=artsList.get(position).date
    }

    override fun getItemCount(): Int {
        return artsList.size
    }
}