package knightly.CurrencyService.service;

import knightly.CurrencyService.enums.Currency;
import knightly.CurrencyService.service.impl.CurrencyExchangerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CurrencyExchangerImplTest {

    private CurrencyExchangerImpl currencyExchangerImpl;

    @BeforeEach
    void setUp() {
        this.currencyExchangerImpl = new CurrencyExchangerImpl();
    }

    @Test
    public void exchangeToBronze() {
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(50, Currency.bronze);
        BigDecimal expectedAmount = new BigDecimal("50.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToSilver(){
         BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(100, Currency.silver);
         BigDecimal expectedAmount = new BigDecimal("10.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToSilverRounding(){
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(1666, Currency.silver);
        BigDecimal expectedAmount = new BigDecimal("166.60");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToSilverZero(){
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(0, Currency.silver);
        BigDecimal expectedAmount = new BigDecimal("0.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToGold(){
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(400, Currency.gold);
        BigDecimal expectedAmount = new BigDecimal("4.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToGoldRounding(){
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(1666, Currency.gold);
        BigDecimal expectedAmount = new BigDecimal("16.66");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToDonkey() {
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(800, Currency.donkey);
        BigDecimal expectedAmount = new BigDecimal("3.20");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToDonkeyRounding() {
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(3333, Currency.donkey);
        BigDecimal expectedAmount = new BigDecimal("13.33");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToCow() {
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(2400, Currency.cow);
        BigDecimal expectedAmount = new BigDecimal("4.80");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToCowRounding() {
        BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(12489, Currency.cow);
        BigDecimal expectedAmount = new BigDecimal("24.98");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }
}
