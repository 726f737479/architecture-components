package com.example.rosty.architecture.data


import com.example.rosty.architecture.data.local.User

import io.reactivex.Observable

interface DataSource {

    fun searchRemoteUsers(searchTerm: String): Observable<List<User>>
    fun searchSavedUsers(searchTerm: String): Observable<List<User>>
    fun getSavedUsers(): Observable<List<User>>

    fun saveUser(user: User)
    fun deleteUser(user: User)
}
