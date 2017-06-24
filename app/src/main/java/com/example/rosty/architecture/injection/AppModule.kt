package com.example.rosty.architecture.injection

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.example.rosty.architecture.ArchiApplication
import com.example.rosty.architecture.data.DataSource
import com.example.rosty.architecture.data.DataSourceImpl
import com.example.rosty.architecture.data.local.AppDataBase
import com.example.rosty.architecture.data.remote.GithubApiService
import com.example.rosty.architecture.react.AppRxSchedulers
import com.example.rosty.architecture.react.RxSchedulers

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class AppModule(private val application: ArchiApplication) {

    @Provides
    internal fun applicationContext(): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideDataSource(dataBase: AppDataBase,
                                   apiService: GithubApiService,
                                   rxSchedulers: RxSchedulers): DataSource {

        return DataSourceImpl(dataBase, apiService, rxSchedulers)
    }

    @Singleton
    @Provides
    internal fun provideRxSchedulers(): RxSchedulers {
        return AppRxSchedulers()
    }

    @Singleton
    @Provides
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    internal fun provideDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "user_db").build()
    }

    @Singleton
    @Provides
    internal fun provideGithubApiService(): GithubApiService {

        val okHttpClient = OkHttpClient.Builder().build()

        val builder = Retrofit.Builder().client(okHttpClient)
                .baseUrl(GithubApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        val retrofit = builder.build()

        return retrofit.create(GithubApiService::class.java)
    }
}
