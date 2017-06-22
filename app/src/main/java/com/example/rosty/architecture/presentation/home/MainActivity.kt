package com.example.rosty.architecture.presentation.home

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.rosty.architecture.R
import com.example.rosty.architecture.databinding.ActivityMainBinding
import com.example.rosty.architecture.injection.AppFactory

class MainActivity : LifecycleActivity() {

    lateinit var viewModel  : MainViewModel
    lateinit var binding    : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding     = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel   = ViewModelProviders.of(this, AppFactory(application )).get(MainViewModel::class.java)

        lifecycle.addObserver(viewModel)
    }
}
