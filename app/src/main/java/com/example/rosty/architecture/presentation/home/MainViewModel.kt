package com.example.rosty.architecture.presentation.home

import com.example.rosty.architecture.data.DataSource
import com.example.rosty.architecture.injection.AppComponent
import com.example.rosty.architecture.presentation.base.BaseViewModel
import javax.inject.Inject

class MainViewModel : BaseViewModel(), AppComponent.Injectable {

    @Inject lateinit var source: DataSource

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    override fun onCleared() {
        super.onCleared()
    }
}