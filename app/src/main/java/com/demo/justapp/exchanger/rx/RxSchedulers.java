package com.demo.justapp.exchanger.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Планировщик для создания потоков
 *
 * @author Sergey Rodionov
 */
public interface RxSchedulers {

    /**
     * Создает планироващик для главного потока
     *
     * @return {@link Scheduler}
     */
    @NonNull
    Scheduler getMainThreadScheduler();

    /**
     * Создает планироващик для worker потока
     *
     * @return {@link Scheduler}
     */
    @NonNull
    Scheduler getIOScheduler();
}
