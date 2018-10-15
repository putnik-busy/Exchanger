package com.demo.justapp.exchanger.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Реализация [RxSchedulers]
 *
 * @author Sergey Rodionov
 */
class RxSchedulersImpl @Inject constructor() : RxSchedulers {

    init {
        System.setProperty("rx2.computation-priority", "" + (Thread.NORM_PRIORITY - 1)) // Process.THREAD_PRIORITY_BACKGROUND
        System.setProperty("rx2.single-priority", "" + (Thread.NORM_PRIORITY - 1)) // Process.THREAD_PRIORITY_BACKGROUND
        System.setProperty("rx2.io-priority", "" + (Thread.MIN_PRIORITY)) // Process.THREAD_PRIORITY_LOWEST
        System.setProperty("rx2.newthread-priority", "" + (Thread.MIN_PRIORITY)) // Process.THREAD_PRIORITY_LOWEST
    }

    override fun getMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun getIOScheduler(): Scheduler {
        return Schedulers.io()
    }

    override fun getComputationScheduler(): Scheduler {
        return Schedulers.computation()
    }

}