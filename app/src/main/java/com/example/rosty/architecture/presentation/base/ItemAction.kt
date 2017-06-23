package com.example.rosty.architecture.presentation.base

import com.example.rosty.architecture.util.Action

/**
 * Created by rosty on 6/23/17.
 */

interface ItemAction<T> {

    fun click(item: T) {}
    fun longClick(item: T) {}
    fun invoke(item: T) {}
}
