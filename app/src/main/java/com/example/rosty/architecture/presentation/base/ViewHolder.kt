package com.example.rosty.architecture.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by rosty on 6/22/17.
 */

class ViewHolder<DB : ViewDataBinding>(view: View) : RecyclerView.ViewHolder(view) {

    val binding: DB

    init { binding = DataBindingUtil.bind<DB>(view) }
}