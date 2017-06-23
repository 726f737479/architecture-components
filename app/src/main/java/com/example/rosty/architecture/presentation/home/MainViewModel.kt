package com.example.rosty.architecture.presentation.home

import com.example.rosty.architecture.injection.AppComponent
import com.example.rosty.architecture.presentation.base.ViewModel

class MainViewModel : ViewModel(), AppComponent.Injectable {

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    override fun onCleared() {
        super.onCleared()
    }
}