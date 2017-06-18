package com.example.rosty.architecture.react

import io.reactivex.Scheduler

interface RxSchedulers {

    fun db(): Scheduler

    fun main(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler
}
