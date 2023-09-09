package com.bangkit.githubprofiledicoding.adapters

import android.text.format.DateUtils
import android.text.format.DateUtils.getRelativeTimeSpanString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.githubprofiledicoding.data.remote.response.User
import com.bangkit.githubprofiledicoding.databinding.ItemUserBinding
import com.bumptech.glide.Glide


class UserListAdapter() :
    ListAdapter<User, UserListAdapter.ViewHolder>(DIFF_CALLBACK) {

    private val _listUser = mutableListOf<User>()

    private lateinit var onItemClickCallback: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickCallback = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onItemClickCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    inner class ViewHolder(
        private val binding: ItemUserBinding,
        itemClickListener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val user = getItem(position)
                    itemClickListener?.onItemClick(user)
                }
            }
        }

        fun bind(user: User) = with(binding) {
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(profileImage)

            tvName.text = user.login
        }
    }

    fun updateData(newData: List<User>) {
        _listUser.clear()
        _listUser.addAll(newData)
        notifyDataSetChanged()
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getRelativeTimeSpanString(time: Long): CharSequence {
        return getRelativeTimeSpanString(
            time,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
            .replace(" minutes ago", " menit yang lalu")
            .replace(" hours ago", " jam yang lalu")
            .replace(" hour ago", " jam yang lalu")
            .replace("Yesterday", " Kemarin")
            .replace(" days ago", " hari yang lalu")
            .replace(" day ago", " hari yang lalu")
            .replace(" weeks ago", " minggu yang lalu")
            .replace(" week ago", " minggu yang lalu")
            .replace(" months ago", " bulan yang lalu")
            .replace(" month ago", " bulan yang lalu")
            .replace(" years ago", " tahun yang lalu")
    }


}
