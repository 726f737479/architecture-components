package com.example.rosty.architecture.presentation.home.users

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rosty.architecture.R
import com.example.rosty.architecture.data.local.User
import com.example.rosty.architecture.databinding.FragmentUsersBinding
import com.example.rosty.architecture.databinding.ItemUserBinding
import com.example.rosty.architecture.injection.AppFactory
import com.example.rosty.architecture.presentation.base.ListAdapter
import com.example.rosty.architecture.util.onTextChange


class UsersFragment : LifecycleFragment() {

    lateinit var viewModel: UsersViewModel
    lateinit var binding:   FragmentUsersBinding
    lateinit var adapter:   ListAdapter<User, ItemUserBinding>

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding     = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        viewModel   = ViewModelProviders.of(this, AppFactory(activity.application)).get(UsersViewModel::class.java)

        lifecycle.addObserver(viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UsersAdapter(R.layout.item_user, viewModel.users, viewModel);

        binding.vm = viewModel
        binding.listUsers.adapter = adapter
        binding.searchBar.query = viewModel.query
        binding.searchBar.etQuery.onTextChange { viewModel.query.set(it) }
        binding.searchBar.btnClear.setOnClickListener { binding.searchBar.etQuery.text.clear() }
    }
}

