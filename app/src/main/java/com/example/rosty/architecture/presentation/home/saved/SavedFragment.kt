package com.example.rosty.architecture.presentation.home.saved

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rosty.architecture.R
import com.example.rosty.architecture.databinding.FragmentSavedBinding
import com.example.rosty.architecture.injection.AppFactory
import com.example.rosty.architecture.presentation.home.search.SavedViewModel


class SavedFragment : LifecycleFragment() {

    lateinit var viewModel  : SavedViewModel
    lateinit var binding    : FragmentSavedBinding

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding     = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        viewModel   = ViewModelProviders.of(this, AppFactory(activity.application)).get(SavedViewModel::class.java)

        lifecycle.addObserver(viewModel)

        return binding.root
    }
}
