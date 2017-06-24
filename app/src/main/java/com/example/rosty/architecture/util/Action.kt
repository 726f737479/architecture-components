package com.example.rosty.architecture.util


import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject


class Action {

    interface Function<T> {

        operator fun invoke() : Observable<T>
    }

    interface Function0 {

        operator fun invoke()
    }

    interface Function1<T> {

        operator fun invoke(param: T)
    }

    interface Function2<T, R> {

        operator fun invoke(param1: T, param2: R)
    }
}
