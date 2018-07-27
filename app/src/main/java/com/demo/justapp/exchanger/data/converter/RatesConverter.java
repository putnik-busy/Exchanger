package com.demo.justapp.exchanger.data.converter;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.data.base.AbstractOneWayConverter;
import com.demo.justapp.exchanger.data.network.RestApi;
import com.demo.justapp.exchanger.models.local.Rate;
import com.demo.justapp.exchanger.models.local.RatesModel;
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
public class RatesConverter extends AbstractOneWayConverter<RatesResponse, RatesModel> {

    @Inject
    public RatesConverter() {
        //необходим для dagger
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public RatesModel convert(@NonNull RatesResponse remote) {
        RatesModel ratesModel = new RatesModel();
        List<Rate> rates = new ArrayList<>();

        ratesModel.setDefaultCurrency(remote.getBase());

        for (Map.Entry<String, Double> entry : remote.getRates().entrySet()) {
            Rate rate = new Rate();
            rate.setCurrency(entry.getKey());
            rate.setRateExchange(entry.getValue());
            rates.add(rate);
        }

        ratesModel.setRates(rates);
        return ratesModel;
    }

}
