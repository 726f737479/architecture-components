package com.example.rosty.architecture.data.remote

import com.example.rosty.architecture.data.remote.model.UserResponse
import com.example.rosty.architecture.data.remote.model.UsersListResponse

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubApiService {

    @GET("/search/users?per_page=2")
    fun searchGithubUsers(@Query("q") searchTerm: String): Observable<Response<UsersListResponse>>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Observable<Response<UserResponse>>

    companion object {

        val BASE_URL = "https://api.github.com"
    }
}
