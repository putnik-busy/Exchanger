package com.demo.justapp.exchanger.data.mapper

import com.demo.justapp.exchanger.CurrencyRatesStubDataProvider.createCurrencyModel
import com.demo.justapp.exchanger.CurrencyRatesStubDataProvider.createRatesResponse
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class RatesMapperTest {

    private val ratesMapper = RatesMapper()

    @Test
    fun `map rates response to currency model`() {
        val ratesResponse = createRatesResponse()
        val expected = createCurrencyModel()

        val actual = ratesMapper.map(ratesResponse)

        assertEquals(expected, actual)
    }
}