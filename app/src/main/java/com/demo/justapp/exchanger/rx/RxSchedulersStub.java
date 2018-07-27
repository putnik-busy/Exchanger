package com.demo.justapp.exchanger.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Стабовая реализация {@link RxSchedulers}
 *
 * @author Sergey Rodionov
 */
public class RxSchedulersStub implements RxSchedulers {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Scheduler getMainThreadScheduler() {
        return Schedulers.trampoline();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.trampoline();
    }

}