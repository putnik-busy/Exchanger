package com.demo.justapp.exchanger.presentation.base

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable

/**
 * Базовый презентер для приложения.
 * <p>
 * Тут будет какая-то общая логика для всех презентеров в приложении.
 *
 * @param <V> интерфейс для представления
 *
 * @author Sergey Rodionov
 */
open class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun getRxCompositeDisposable(): CompositeDisposable = mCompositeDisposable

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
    }

}