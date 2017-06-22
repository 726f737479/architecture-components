package com.example.rosty.architecture.data.local

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface UserDao {

    @get:Query("SELECT * FROM user")
    val all: Flowable<List<User>>

    @Query("SELECT * FROM user WHERE login LIKE :arg0")
    fun searchInSavedUser(searchTerm: String): Flowable<List<User>>

    @Query("SELECT * FROM user WHERE id LIKE :arg0 LIMIT 1")
    fun findByID(id: Int): User?

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)
}
