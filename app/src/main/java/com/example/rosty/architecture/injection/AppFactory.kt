package com.example.rosty.architecture.injection

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.example.rosty.architecture.ArchiApplication
/**
 * @author rebeccafranks
 * *
 * @since 2017/05/10.
 */

class AppFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val t = super.create(modelClass)

        if (t is AppComponent.Injectable)
            t.inject((application as ArchiApplication).appComponent)

        return t
    }
}
