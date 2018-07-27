package com.demo.justapp.exchanger.presentation.base;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Базовый презентер для приложения.
 * <p>
 * Тут будет какая-то общая логика для всех презентеров в приложении.
 *
 * @param <V> интерфейс для представления
 * @author Sergey Rodionov
 */
public class BasePresenter<V extends BaseView> extends MvpPresenter<V> {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Возвращает {@link CompositeDisposable}, который очистит ссылки на {@link #onDestroy()}.
     *
     * @return {@link CompositeDisposable}
     */
    protected final CompositeDisposable getRxCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

}
