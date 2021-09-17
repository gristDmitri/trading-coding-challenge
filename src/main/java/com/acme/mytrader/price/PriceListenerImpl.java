package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImpl implements PriceListener {

    private ExecutionService executionService;
    private String securityName;
    private double priceThreshold;
    private int buyVolume;

    public PriceListenerImpl(ExecutionService executionService, String securityName, double priceThreshold, int buyVolume) {
        this.executionService = executionService;
        this.securityName = securityName;
        this.priceThreshold = priceThreshold;
        this.buyVolume = buyVolume;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if(securityName.equals(security) && price <= priceThreshold ) {
            executionService.buy(security, price, buyVolume);
        }
    }

}
