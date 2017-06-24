package com.example.rosty.architecture.presentation.home.repos

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rosty.architecture.R
import com.example.rosty.architecture.databinding.FragmentReposBinding
import com.example.rosty.architecture.injection.AppFactory
import com.example.rosty.architecture.presentation.home.users.UsersViewModel


class ReposFragment : LifecycleFragment() {

    lateinit var viewModel: UsersViewModel
    lateinit var binding:   FragmentReposBinding

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding     = DataBindingUtil.inflate(inflater, R.layout.fragment_repos, container, false)
        viewModel   = ViewModelProviders.of(this, AppFactory(activity.application)).get(UsersViewModel::class.java)

        lifecycle.addObserver(viewModel)

        return binding.root
    }
}
