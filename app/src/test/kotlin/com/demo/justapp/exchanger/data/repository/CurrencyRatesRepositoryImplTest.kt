package com.demo.justapp.exchanger.data.repository

import com.demo.justapp.exchanger.BASE_CURRENCY
import com.demo.justapp.exchanger.CurrencyRatesStubDataProvider.createCurrencyModel
import com.demo.justapp.exchanger.CurrencyRatesStubDataProvider.createRatesResponse
import com.demo.justapp.exchanger.data.api.CurrencyRatesApi
import com.demo.justapp.exchanger.data.mapper.RatesMapper
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import io.reactivex.Single
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CurrencyRatesRepositoryImplTest {

    @MockK
    private lateinit var currencyRatesApi: CurrencyRatesApi

    @MockK
    private lateinit var ratesMapper: RatesMapper

    @InjectMockKs
    private lateinit var repository: CurrencyRatesRepositoryImpl

    @Test
    fun `load currency rates when base currency EUR`() {
        val response = createRatesResponse()
        val currencyModel = createCurrencyModel()
        every { ratesMapper.map(response) } returns currencyModel
        every { currencyRatesApi.getCurrencyRates(BASE_CURRENCY) } returns Single.just(response)

        repository.loadCurrencyRates(BASE_CURRENCY)
                .test()
                .assertValue(currencyModel)

        verify { currencyRatesApi.getCurrencyRates(BASE_CURRENCY) }
    }
}