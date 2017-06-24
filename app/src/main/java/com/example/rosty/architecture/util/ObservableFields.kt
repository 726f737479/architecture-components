package com.example.rosty.architecture.util

import android.databinding.ObservableField
import android.databinding.ObservableList
import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

import java.util.ArrayList

fun <T> ObservableList<T>.applyDiff(changed: List<T>, diffCallback: DiffUtil.Callback) {

    val diffResult = DiffUtil.calculateDiff(diffCallback)

    diffResult.dispatchUpdatesTo(object : ListUpdateCallback {

        override fun onInserted(position: Int, count: Int) {

            val inserted = ArrayList<T>()

            for (i in position..position + count - 1) {
                inserted.add(changed[i])
            }

            this@applyDiff.addAll(position, inserted)
        }

        override fun onRemoved(position: Int, count: Int) {

            this@applyDiff.subList(position, position + count).clear()
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {

            val t =  this@applyDiff[fromPosition]

            this@applyDiff.removeAt(fromPosition)
            this@applyDiff.add(toPosition, t)
        }

        override fun onChanged(position: Int, count: Int, payload: Any) {

            for (i in position..position + count - 1)  this@applyDiff[i] = changed[i]
        }
    })
}

fun <T> ObservableField<T>.toObservable(): Observable<T> {

    return Observable.create { emitter ->

        val callback = object : android.databinding.Observable.OnPropertyChangedCallback() {

            override fun onPropertyChanged(dataBindingObservable: android.databinding.Observable, propertyId: Int) {

                if (dataBindingObservable === this@toObservable)
                    emitter.onNext(this@toObservable.get())
            }
        }

        this@toObservable.addOnPropertyChangedCallback(callback)

        emitter.setDisposable(object : Disposable {

            override fun dispose() {
                this@toObservable.removeOnPropertyChangedCallback(callback)
            }

            override fun isDisposed(): Boolean {
                return false
            }
        })
    }
}



