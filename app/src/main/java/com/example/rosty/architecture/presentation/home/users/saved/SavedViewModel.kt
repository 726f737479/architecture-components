package com.example.rosty.architecture.presentation.home.users.saved

import android.databinding.ObservableArrayList
import com.example.rosty.architecture.data.local.User
import com.example.rosty.architecture.presentation.home.users.UsersViewModel

class SavedViewModel: UsersViewModel() {

    val users = ObservableArrayList<User>()

    override fun invoke(item: User) {

    }
}