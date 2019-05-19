package com.demo.justapp.exchanger.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Базовый презентер для приложения.
 *
 * @param [V] интерфейс для вью
 *
 */
open class BasePresenter<V : MvpView> : MvpPresenter<V>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun Disposable.untilDestroy() {
        compositeDisposable.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}