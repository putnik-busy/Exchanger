package com.demo.justapp.exchanger.models.remote;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Ответ от сервера, содержащий полную информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
public class RatesResponse {

    @SerializedName("base")
    public String base;
    @SerializedName("date")
    public String date;
    @SerializedName("rates")
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RatesResponse that = (RatesResponse) o;

        if (base != null ? !base.equals(that.base) : that.base != null) {
            return false;
        }
        if (date != null ? !date.equals(that.date) : that.date != null) {
            return false;
        }
        return rates != null ? rates.equals(that.rates) : that.rates == null;
    }

    @Override
    public int hashCode() {
        int result = base != null ? base.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (rates != null ? rates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RatesResponse{" +
                "base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
