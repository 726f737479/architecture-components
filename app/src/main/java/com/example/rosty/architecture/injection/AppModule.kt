package com.example.rosty.architecture.injection

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.example.rosty.architecture.ArchiApplication
import com.example.rosty.architecture.data.local.AppDataBase
import com.example.rosty.architecture.data.remote.GithubApiService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author rebeccafranks
 * *
 * @since 2017/05/11.
 */
@Module
class AppModule(private val application: ArchiApplication) {

    @Provides
    internal fun applicationContext(): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    internal fun provideDataSource(context: Context): AppDataBase {

        return Room.databaseBuilder(context, AppDataBase::class.java, "database").build()
    }

    @Singleton
    @Provides
    internal fun provideGithubApiService(): GithubApiService {

        val okHttpClient = OkHttpClient.Builder().build()

        val builder = Retrofit.Builder().client(okHttpClient)
                .baseUrl(GithubApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

        val retrofit = builder.build()

        return retrofit.create(GithubApiService::class.java)
    }
}
