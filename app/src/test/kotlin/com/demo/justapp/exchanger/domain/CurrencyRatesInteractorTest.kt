package com.demo.justapp.exchanger.domain

import com.demo.justapp.exchanger.CurrencyRatesStubDataProvider
import com.demo.justapp.exchanger.domain.interactor.CurrencyRatesInteractor
import com.demo.justapp.exchanger.domain.repository.CurrencyRatesRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import io.reactivex.Single
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

private const val BASE_CURRENCY = "EUR"

@ExtendWith(MockKExtension::class)
class CurrencyRatesInteractorTest {

    @MockK
    private lateinit var repository: CurrencyRatesRepository

    @InjectMockKs
    private lateinit var interactor: CurrencyRatesInteractor

    @Test
    fun `load currency rates when base currency EUR`() {
        val currencyModel = CurrencyRatesStubDataProvider.createCurrencyModel()
        every { repository.loadCurrencyRates(BASE_CURRENCY) } returns Single.just(currencyModel)

        interactor.loadCurrencyRates(BASE_CURRENCY)
                .test()
                .assertValue(currencyModel)

        verify { repository.loadCurrencyRates(BASE_CURRENCY) }
    }
}