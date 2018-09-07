package com.demo.justapp.exchanger.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * @author Sergey Rodionov
 */
class RxSchedulersStub : RxSchedulers {

    override fun getIOScheduler(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun getMainThreadScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}