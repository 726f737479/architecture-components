package com.example.rosty.architecture.presentation

import android.arch.lifecycle.Lifecycle
import android.util.Log
import com.example.rosty.architecture.data.DataSource
import com.example.rosty.architecture.injection.AppComponent
import com.example.rosty.architecture.util.Action
import javax.inject.Inject

class MainViewModel : BaseViewModel(), AppComponent.Injectable {

    lateinit var showErrorDialog : Action.Function<Int>
//
    @Inject lateinit var source: DataSource

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)

        source.searchRemoteUsers("rostDev")
                .subscribe{list -> list.forEach {user -> Log.d("D", user.toString())} }
    }

    override fun onCleared() {
        super.onCleared()
    }
}