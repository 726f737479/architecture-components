package com.example.rosty.architecture.data

import com.example.rosty.architecture.data.local.AppDataBase
import com.example.rosty.architecture.data.local.User
import com.example.rosty.architecture.data.remote.GithubApiService
import com.example.rosty.architecture.react.RxSchedulers

import java.io.IOException

import javax.inject.Inject

import io.reactivex.Observable


class DataSourceImpl @Inject constructor(private val dataBase: AppDataBase,
                                         private val githubApiService: GithubApiService,
                                         private val schedulers: RxSchedulers) : DataSource {

    override fun searchRemoteUsers(searchTerm: String): Observable<List<User>> {

        return githubApiService.searchGithubUsers(searchTerm)
                .subscribeOn(schedulers.io())
                .concatMap { response -> Observable.fromIterable(response.body().items)

                        .concatMap { userResponse -> githubApiService.getUser(userResponse.login) }
                        .map { response -> User(response.body()) }
                        .doOnNext { user -> user.isSaved = dataBase.userDao().findByID(user.id!!) != null }
                        .toList().toObservable()
                }
                .retryWhen { throwableObservable -> throwableObservable
                        .flatMap { e -> Observable.error<Throwable> { if (e is IOException) RuntimeException(e) else e } } }
    }

    override fun searchSavedUsers(searchTerm: String): Observable<List<User>> {

        return dataBase.userDao().searchInSavedUser(searchTerm)
                .subscribeOn(schedulers.db())
    }

    override fun getSavedUsers(): Observable<List<User>> {

        return dataBase.userDao().all
                .subscribeOn(schedulers.db())
    }

    override fun saveUser(user: User) {

        dataBase.userDao().insertAll(user)
    }

    override fun deleteUser(user: User) {

        dataBase.userDao().delete(user)
    }
}
