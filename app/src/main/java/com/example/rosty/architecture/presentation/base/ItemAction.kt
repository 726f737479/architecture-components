package com.example.rosty.architecture.presentation.base

interface ItemAction<T> {

    fun click(item: T) {}
    fun longClick(item: T) {}
    fun invoke(item: T) {}
}
