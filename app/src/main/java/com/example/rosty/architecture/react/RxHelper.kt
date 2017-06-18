package com.example.rosty.architecture.react

import rx.Observable
import rx.functions.Func0

object RxHelper {

    /**
     * Method that create observable for single item data

     * @param function methods that return data item
     * *
     * @param <T> type of item
     * *
     * *
     * @return Observable of specific type
    </T> */
    fun <T> createSingleData(function: Func0<T>): Observable<T> {
        return Observable.create<T> { subscriber ->
            try {
                val data = function.call()
                subscriber.onNext(data)
                subscriber.onCompleted()
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }
    }
}
