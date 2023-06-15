package com.example.upjaoassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.upjaoassignment.BR
import com.example.upjaoassignment.R
import com.example.upjaoassignment.databinding.ItemListBinding
import com.example.upjaoassignment.model.User


class ListDataAdapter(
    private var userList: List<User?>?
) :
    RecyclerView.Adapter<ListDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemListBinding = ItemListBinding.inflate(inflater, parent, false)
        return ViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemListBinding.imgProfile).load(userList?.get(position)?.profilePic)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(holder.itemListBinding.imgProfile)

        holder.bind(userList?.get(position))
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    fun setListData(it: List<User?>?) {
        if (!it.isNullOrEmpty())
            this.userList = it
    }

    inner class ViewHolder(val itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(listData: User?) {
            itemListBinding.setVariable(BR.listData, listData)
            itemListBinding.executePendingBindings()
        }
    }
}