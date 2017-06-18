package com.example.rosty.architecture.data


import com.example.rosty.architecture.data.local.User
import io.reactivex.Flowable

import io.reactivex.Observable

interface DataSource {

    fun searchRemoteUsers(searchTerm: String): Observable<List<User>>
    fun searchSavedUsers(searchTerm: String): Flowable<List<User>>
    fun getSavedUsers(): Flowable<List<User>>

    fun saveUser(user: User)
    fun deleteUser(user: User)
}
