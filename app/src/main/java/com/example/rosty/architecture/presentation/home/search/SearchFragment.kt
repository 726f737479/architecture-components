package com.example.rosty.architecture.presentation.home.search

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rosty.architecture.R
import com.example.rosty.architecture.databinding.FragmentSerachBinding
import com.example.rosty.architecture.injection.AppFactory


class SearchFragment : LifecycleFragment() {

    lateinit var viewModel  : SearchViewModel
    lateinit var binding    : FragmentSerachBinding

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding     = DataBindingUtil.inflate(inflater, R.layout.fragment_serach, container, false)
        viewModel   = ViewModelProviders.of(this, AppFactory(activity.application)).get(SearchViewModel::class.java)

        lifecycle.addObserver(viewModel)

        return binding.root
    }
}
