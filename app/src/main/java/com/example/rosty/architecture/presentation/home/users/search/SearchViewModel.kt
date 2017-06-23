package com.example.rosty.architecture.presentation.home.users.search

import android.databinding.ObservableArrayList
import com.example.rosty.architecture.data.local.User
import com.example.rosty.architecture.presentation.home.users.UsersViewModel

class SearchViewModel : UsersViewModel() {

    val users = ObservableArrayList<User>()

    override fun invoke(item: User) {
    }
}