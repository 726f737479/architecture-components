package com.example.rosty.architecture.presentation

import com.example.rosty.architecture.injection.AppComponent
import com.example.rosty.architecture.util.Action

class MainViewModel : BaseViewModel(), AppComponent.Injectable {

    lateinit var showErrorDialog : Action.Function<Int>

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    override fun onCleared() {
        super.onCleared()

    }
}