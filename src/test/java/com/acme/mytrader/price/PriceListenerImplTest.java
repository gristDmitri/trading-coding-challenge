package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PriceListenerImplTest {

    private static final String SECURITY_NAME = "IBM";
    private static final String DIFFERENT_SECURITY_NAME = "AMC";
    private static final double PRICE_THRESHOLD = 130.0;
    private static final int BUY_VOLUME = 50;

    @Mock
    private ExecutionService executionService;

    @Test
    public void testPriceUpdateMatchingSecurityPassesThreshold() {
        PriceListenerImpl priceListener = new PriceListenerImpl(executionService, SECURITY_NAME, PRICE_THRESHOLD, BUY_VOLUME);

        double price = PRICE_THRESHOLD - 1.0;
        priceListener.priceUpdate(SECURITY_NAME, price);

        verify(executionService).buy(SECURITY_NAME, price, BUY_VOLUME);
    }

    @Test
    public void testPriceUpdateMatchingSecurityDoesNotPassThreshold() {
        PriceListenerImpl priceListener = new PriceListenerImpl(executionService, SECURITY_NAME, PRICE_THRESHOLD, BUY_VOLUME);

        double price = PRICE_THRESHOLD + 1.0;
        priceListener.priceUpdate(SECURITY_NAME, price);

        verify(executionService, never()).buy(SECURITY_NAME, price, BUY_VOLUME);
    }

    @Test
    public void testPriceUpdateNotMatchingSecurityPassesThreshold() {
        PriceListenerImpl priceListener = new PriceListenerImpl(executionService, SECURITY_NAME, PRICE_THRESHOLD, BUY_VOLUME);

        double price = PRICE_THRESHOLD - 1.0;
        priceListener.priceUpdate(DIFFERENT_SECURITY_NAME, price);

        verify(executionService, never()).buy(SECURITY_NAME, price, BUY_VOLUME);
    }

    @Test
    public void testPriceUpdateNotMatchingSecurityDoesNotPassThreshold() {
        PriceListenerImpl priceListener = new PriceListenerImpl(executionService, SECURITY_NAME, PRICE_THRESHOLD, BUY_VOLUME);

        double price = PRICE_THRESHOLD - 1.0;
        priceListener.priceUpdate(DIFFERENT_SECURITY_NAME, price);

        verify(executionService, never()).buy(SECURITY_NAME, price, BUY_VOLUME);
    }

}