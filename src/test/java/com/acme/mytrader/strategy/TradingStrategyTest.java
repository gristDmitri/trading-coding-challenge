package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {

    @Mock
    private PriceSource priceSource;

    @Test
    public void testConstructorAddsListeners() {
        PriceListener priceListener1 = Mockito.mock(PriceListener.class);
        PriceListener priceListener2 = Mockito.mock(PriceListener.class);
        PriceListener priceListener3 = Mockito.mock(PriceListener.class);

        TradingStrategy tradingStrategy = new TradingStrategy(priceSource, priceListener1, priceListener2, priceListener3);

        verify(priceSource).addPriceListener(priceListener1);
        verify(priceSource).addPriceListener(priceListener2);
        verify(priceSource).addPriceListener(priceListener3);
    }

}
