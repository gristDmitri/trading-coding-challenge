package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    public TradingStrategy(PriceSource priceSource, PriceListener... priceListeners) {
        addPriceListeners(priceSource, priceListeners);
    }

    private void addPriceListeners(PriceSource priceSource, PriceListener... priceListeners) {
        for(PriceListener priceListener : priceListeners) {
            priceSource.addPriceListener(priceListener);
        }
    }

}
