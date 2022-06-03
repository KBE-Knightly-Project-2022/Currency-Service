package knightly.CurrencyService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CurrencyExchangerTest {

    private String SILVER = "silver";
    private String GOLD = "gold";
    private String COW = "cow";
    private String DONKEY = "donkey";

    @Test
    public void exchanceToSilverValid(){
         BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(100, SILVER);
         BigDecimal expectedAmount = new BigDecimal("10.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchanceToSilverRoundingValid(){
        BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(1666, SILVER);
        BigDecimal expectedAmount = new BigDecimal("166.60");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchanceToSilverZeroValid(){
        BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(0, SILVER);
        BigDecimal expectedAmount = new BigDecimal("0.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchanceToGoldValid(){
        BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(400, GOLD);
        BigDecimal expectedAmount = new BigDecimal("4.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchanceToGoldRoundingValid(){
        BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(1666, GOLD);
        BigDecimal expectedAmount = new BigDecimal("16.66");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToDonkeyValid() {
        BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(800, DONKEY);
        BigDecimal expectedAmount = new BigDecimal("2.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToDonkeyRoundingValid() {
        BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(3333, DONKEY);
        BigDecimal expectedAmount = new BigDecimal("8.33");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToCowValid() {
        BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(2400, COW);
        BigDecimal expectedAmount = new BigDecimal("3.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void echangeToCowRoundingValid() {
        BigDecimal exchangedCurrency = CurrencyExchanger.exchangeCurrency(12489, COW);
        BigDecimal expectedAmount = new BigDecimal("15.61");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }
}
