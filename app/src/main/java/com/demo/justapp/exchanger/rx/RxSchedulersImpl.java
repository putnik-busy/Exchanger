package com.demo.justapp.exchanger.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Реализация {@link RxSchedulers}
 *
 * @author Sergey Rodionov
 */
public class RxSchedulersImpl implements RxSchedulers {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

}