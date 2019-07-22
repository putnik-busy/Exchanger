package com.demo.justapp.exchanger.domain

import com.demo.justapp.exchanger.CurrencyRatesStubDataProvider
import com.demo.justapp.exchanger.domain.repository.CurrencyRatesRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

private const val BASE_CURRENCY = "EUR"

@RunWith(MockitoJUnitRunner::class)
class CurrencyRatesInteractorTest {

    @Mock
    private lateinit var repository: CurrencyRatesRepository

    @InjectMocks
    private lateinit var interactor: CurrencyRatesInteractor

    @Test
    fun `load currency rates when base currency EUR`() {
        val currencyModel = CurrencyRatesStubDataProvider.createCurrencyModel()
        whenever(repository.loadCurrencyRates(BASE_CURRENCY)).thenReturn(Single.just(currencyModel))

        interactor.loadCurrencyRates(BASE_CURRENCY)
            .test()
            .assertValue(currencyModel)

        verify(repository).loadCurrencyRates(BASE_CURRENCY)
    }
}