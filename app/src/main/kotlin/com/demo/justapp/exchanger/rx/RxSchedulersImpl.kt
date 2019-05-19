package com.demo.justapp.exchanger.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Реализация [RxSchedulers] для управления переключения потоками
 *
 * @author Sergey Rodionov
 */
@Singleton
class RxSchedulersImpl @Inject constructor() : RxSchedulers {

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