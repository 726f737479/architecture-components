package com.example.rosty.architecture.presentation.home.users.search

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rosty.architecture.R
import com.example.rosty.architecture.data.local.User
import com.example.rosty.architecture.databinding.FragmentSerachBinding
import com.example.rosty.architecture.databinding.ItemUserBinding
import com.example.rosty.architecture.injection.AppFactory
import com.example.rosty.architecture.presentation.base.ItemAction
import com.example.rosty.architecture.presentation.base.ListAdapter
import com.example.rosty.architecture.presentation.base.ViewHolder
import com.example.rosty.architecture.presentation.home.users.UsersAdapter
import com.example.rosty.architecture.presentation.home.users.saved.SavedFragment


class SearchFragment : LifecycleFragment() {

    lateinit var viewModel: SearchViewModel
    lateinit var binding:   FragmentSerachBinding
    lateinit var adapter:   ListAdapter<User, ItemUserBinding>

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding     = DataBindingUtil.inflate(inflater, R.layout.fragment_serach, container, false)
        viewModel   = ViewModelProviders.of(this, AppFactory(activity.application)).get(SearchViewModel::class.java)

        lifecycle.addObserver(viewModel)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = UsersAdapter(R.layout.item_user, viewModel.users, viewModel);
    }
}
