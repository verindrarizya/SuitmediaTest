package com.verindrarizya.suitmediatest.ui.thirdscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.verindrarizya.suitmediatest.R
import com.verindrarizya.suitmediatest.data.entity.User
import com.verindrarizya.suitmediatest.databinding.UserItemBinding

class UserAdapter(private val onItemClicked: (User) -> Unit): ListAdapter<User, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userItemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(userItemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvFullname.text = "${user.firstName} ${user.lastName}"
            binding.tvEmail.text = user.email

            Glide.with(itemView.context)
                .load(user.avatar)
                .placeholder(R.drawable.grey_circle)
                .transition(withCrossFade())
                .circleCrop()
                .into(binding.userAvatar)

            itemView.setOnClickListener { onItemClicked(user) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}