package com.demo.justapp.exchanger.data.repository

import com.demo.justapp.exchanger.BASE_CURRENCY
import com.demo.justapp.exchanger.CurrencyRatesStubDataProvider.createCurrencyModel
import com.demo.justapp.exchanger.CurrencyRatesStubDataProvider.createRatesResponse
import com.demo.justapp.exchanger.data.api.CurrencyRatesApi
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CurrencyRatesRepositoryImplTest {

    @Mock
    private lateinit var currencyRatesApi: CurrencyRatesApi

    @InjectMocks
    private lateinit var repository: CurrencyRatesRepositoryImpl

    @Test
    fun `load currency rates when base currency EUR`() {
        val response = createRatesResponse()
        val currencyModel = createCurrencyModel()
        whenever(currencyRatesApi.getCurrencyRates(BASE_CURRENCY)).thenReturn(Single.just(response))

        repository.loadCurrencyRates(BASE_CURRENCY)
            .test()
            .assertValue(currencyModel)

        verify(currencyRatesApi).getCurrencyRates(BASE_CURRENCY)
    }
}