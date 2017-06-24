package com.example.rosty.architecture.presentation.home.users

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.support.v7.util.DiffUtil
import com.example.rosty.architecture.data.DataSource
import com.example.rosty.architecture.data.local.User
import com.example.rosty.architecture.injection.AppComponent
import com.example.rosty.architecture.presentation.base.ItemAction
import com.example.rosty.architecture.presentation.base.ViewModel
import com.example.rosty.architecture.react.RxSchedulers
import com.example.rosty.architecture.util.applyDiff
import com.example.rosty.architecture.util.toObservable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class UsersViewModel : ViewModel(), AppComponent.Injectable, ItemAction<User> {

    @Inject lateinit var source: DataSource
    @Inject lateinit var schedulers: RxSchedulers;

    private val searchUsers = ArrayList<User>()
    private val savedUsers  = ArrayList<User>()

    private var isSaved = false;

    val users = ObservableArrayList<User>()
    val query = ObservableField("")
    val state = ObservableField(State.EMPTY)

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)

        query.toObservable()
                .filter({ term -> isSaved || !term.isEmpty() })
                .debounce(if (!isSaved) 1 else 0, TimeUnit.SECONDS)
                .doOnNext{ query -> state.set(State.PROGRESS) }
                .switchMap{ query -> source.searchRemoteUsers(query)}
                .observeOn(schedulers.main())
                .doOnNext{ applyDiff(users, it) }
                .subscribe(
                        { state.set(if (it.isEmpty()) State.NO_RESULT else State.RESULT) },
                        { state.set(State.ERROR) }
                )
    }

    protected fun applyDiff(base: ObservableList<User>, changed: List<User>) {

        base.applyDiff(changed, object : DiffUtil.Callback() {

            override fun getOldListSize(): Int {
                return base.size
            }

            override fun getNewListSize(): Int {
                return changed.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return base[oldItemPosition].login.equals(changed[newItemPosition].login)
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return base[oldItemPosition] == changed[newItemPosition]
            }
        })
    }

    enum class State { RESULT, PROGRESS, EMPTY, NO_RESULT, ERROR }
}
