package com.example.rosty.architecture

import android.app.Application

import com.example.rosty.architecture.injection.AppComponent
import com.example.rosty.architecture.injection.AppModule
import com.example.rosty.architecture.injection.DaggerAppComponent

/**
 * Created by rosty on 6/18/17.
 */

class ArchiApplication : Application() {

    val appComponent = createAppComponent()

    protected fun createAppComponent(): AppComponent {

        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
