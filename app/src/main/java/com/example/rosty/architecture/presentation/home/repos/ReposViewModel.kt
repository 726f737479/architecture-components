package com.example.rosty.architecture.presentation.home.repos

import com.example.rosty.architecture.data.DataSource
import com.example.rosty.architecture.data.local.User
import com.example.rosty.architecture.injection.AppComponent
import com.example.rosty.architecture.presentation.base.ItemAction
import com.example.rosty.architecture.presentation.base.ViewModel
import com.example.rosty.architecture.react.RxSchedulers
import javax.inject.Inject


class ReposViewModel : ViewModel(), AppComponent.Injectable, ItemAction<User> {

    @Inject lateinit var source: DataSource
    @Inject lateinit var schedulers: RxSchedulers;

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}
