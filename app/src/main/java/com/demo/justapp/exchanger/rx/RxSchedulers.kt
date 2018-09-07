package com.demo.justapp.exchanger.rx

import io.reactivex.Scheduler

/**
 * Планировщик для создания потоков
 *
 * @author Sergey Rodionov
 */
interface RxSchedulers {

    /**
     * Создает планироващик для главного потока
     *
     * @return {@link Scheduler}
     */
    fun getMainThreadScheduler(): Scheduler

    /**
     * Создает планироващик для worker потока
     *
     * @return {@link Scheduler}
     */
    fun getIOScheduler(): Scheduler

}