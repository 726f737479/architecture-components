package com.example.rosty.architecture.react

import android.os.Process
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

import javax.inject.Inject


class AppRxSchedulers @Inject constructor() : RxSchedulers {

    private val db: Scheduler

    init {

        val dbExecutor = ThreadPoolExecutor(
                DB_CORE_POOL_SIZE, DB_MAX_POOL_SIZE, DB_THREAD_KEEP_ALIVE, TimeUnit.SECONDS,
                LinkedBlockingQueue<Runnable>(),
                PriorityThreadFactory(THREAD_NAME, Process.THREAD_PRIORITY_BACKGROUND))

        dbExecutor.allowCoreThreadTimeOut(true)

        db = Schedulers.from(dbExecutor)
    }

    /**
     * @return Scheduler that have to bes used for DB transaction
     */
    override fun db(): Scheduler {
        return db
    }

    /**
     * @return Scheduler that have to bes used to interact with UI components
     */
    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    /**
     * @return Scheduler that can be event-loops, processing callbacks
     * *          and other computational work.
     */
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    /**
     * @return Scheduler can be used for asynchronously performing blocking IO.
     */
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    internal inner class PriorityThreadFactory(private val name: String, private val threadPriority: Int) : ThreadFactory {

        private val number = AtomicInteger()

        override fun newThread(r: Runnable): Thread {

            val name = this.name + "-" + number.getAndIncrement().toString()

            return object : Thread(r, name) {

                override fun run() {

                    try { Process.setThreadPriority(threadPriority)

                    } catch (e: UnsatisfiedLinkError) {}

                    super.run()
                }
            }
        }
    }

    companion object {

        private val THREAD_NAME = "db-thread-pool"
        private val DB_THREAD_KEEP_ALIVE = 10L
        private val DB_CORE_POOL_SIZE = 1
        private val DB_MAX_POOL_SIZE = 1
    }
}
