package com.example.rosty.architecture.presentation

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import com.example.rosty.architecture.injection.AppComponent

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

open class BaseViewModel : ViewModel(), LifecycleObserver{

    private val lifecycle       = BehaviorSubject.create<Lifecycle.Event>()
    private val lifecycleOwner  = BehaviorSubject.create<LifecycleRegistryOwner>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        lifecycle.onNext(Lifecycle.Event.ON_CREATE)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        lifecycle.onNext(Lifecycle.Event.ON_START)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        lifecycle.onNext(Lifecycle.Event.ON_RESUME)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        lifecycle.onNext(Lifecycle.Event.ON_PAUSE)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        lifecycle.onNext(Lifecycle.Event.ON_STOP)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifecycle.onNext(Lifecycle.Event.ON_DESTROY)
    }

    fun <T> Observable<T>.bindUntilEvent(event: Lifecycle.Event): Observable<T> {
        return takeUntil(lifecycle.takeWhile { e -> e != event })
    }
}
