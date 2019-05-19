package com.demo.justapp.exchanger.di.application

/**
 * Класс, реализующий этот интерфейс, возвращает Dagger компонент
 *
 * @param T класс компонента
 */
interface HasComponent<T> {

    fun getComponent(): T
}