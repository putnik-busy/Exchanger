package com.demo.justapp.exchanger.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Реализация [RxSchedulers]
 *
 * @author Sergey Rodionov
 */
class RxSchedulersImpl : RxSchedulers {

    override fun getMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun getIOScheduler(): Scheduler {
        return Schedulers.io()
    }

}