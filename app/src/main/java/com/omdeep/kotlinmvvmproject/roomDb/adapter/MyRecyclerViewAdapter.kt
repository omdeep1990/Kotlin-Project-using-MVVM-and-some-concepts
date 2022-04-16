package com.omdeep.kotlinmvvmproject.roomDb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omdeep.kotlinmvvmproject.databinding.ListItemsBinding
import com.omdeep.kotlinmvvmproject.roomDb.db.User

class MyRecyclerViewAdapter(private val userList : List<User>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemsBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class MyViewHolder(private val binding : ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(user: User){
        binding.yourName.text = user.firstName+" "+user.lastName
        binding.mobileNumber.text = user.mobileNo
    }
}