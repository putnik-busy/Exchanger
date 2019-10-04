package com.demo.justapp.exchanger.data.base

/**
 * Base interface for mapping from [F] model to [T] model
 */
interface Mapper<F, T> {

    fun map(from: F): T

}