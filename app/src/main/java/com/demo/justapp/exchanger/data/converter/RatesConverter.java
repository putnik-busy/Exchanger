package com.demo.justapp.exchanger.data.converter;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.data.base.AbstractOneWayConverter;
import com.demo.justapp.exchanger.data.network.RestApi;
import com.demo.justapp.exchanger.models.local.CurrencyRate;
import com.demo.justapp.exchanger.models.local.CurrencyRatesModel;
import com.demo.justapp.exchanger.models.remote.RatesResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Реализация {@link AbstractOneWayConverter} для запроса {@link RestApi#getRates(String)}()}
 *
 * @author Sergey Rodionov
 */
public class RatesConverter extends AbstractOneWayConverter<RatesResponse, CurrencyRatesModel> {

    @Inject
    public RatesConverter() {
        //необходим для dagger
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public CurrencyRatesModel convert(@NonNull RatesResponse remote) {
        CurrencyRatesModel currencyRatesModel = new CurrencyRatesModel();
        List<CurrencyRate> rates = new ArrayList<>();

        currencyRatesModel.setDefaultCurrency(remote.getBase());

        for (Map.Entry<String, Double> entry : remote.getRates().entrySet()) {
            CurrencyRate rate = new CurrencyRate();
            rate.setCurrency(entry.getKey());
            rate.setRateExchange(entry.getValue());
            rates.add(rate);
        }

        currencyRatesModel.setRates(rates);
        return currencyRatesModel;
    }

}
