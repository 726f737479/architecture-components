package com.example.rosty.architecture.presentation.home.users

import android.databinding.ObservableList
import com.example.rosty.architecture.data.local.User
import com.example.rosty.architecture.databinding.ItemUserBinding
import com.example.rosty.architecture.presentation.base.ItemAction
import com.example.rosty.architecture.presentation.base.ListAdapter
import com.example.rosty.architecture.presentation.base.ViewHolder

/**
 * Created by rosty on 6/23/17.
 */

class UsersAdapter(layoutRes: Int, data: ObservableList<User>?, action: ItemAction<User>)

    : ListAdapter<User, ItemUserBinding>(layoutRes, data, action) {

    override fun onBindViewHolder(holder: ViewHolder<ItemUserBinding>?, position: Int) {

        holder?.binding?.action = action
        holder?.binding?.user = getItem(position)
    }
}
