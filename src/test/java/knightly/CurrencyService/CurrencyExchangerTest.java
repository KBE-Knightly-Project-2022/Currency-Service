package knightly.CurrencyService;

import knightly.CurrencyService.service.CurrencyExchanger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CurrencyExchangerTest {

    private String SILVER = "silver";
    private String GOLD = "gold";
    private String COW = "cow";
    private String DONKEY = "donkey";
    private CurrencyExchanger currencyExchanger;

    @BeforeEach
    void setUp() {
        this.currencyExchanger = new CurrencyExchanger();
    }

    @Test
    public void exchangeToSilverValid(){
         BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(100, SILVER);
         BigDecimal expectedAmount = new BigDecimal("10.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToSilverRoundingValid(){
        BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(1666, SILVER);
        BigDecimal expectedAmount = new BigDecimal("166.60");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToSilverZeroValid(){
        BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(0, SILVER);
        BigDecimal expectedAmount = new BigDecimal("0.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToGoldValid(){
        BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(400, GOLD);
        BigDecimal expectedAmount = new BigDecimal("4.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToGoldRoundingValid(){
        BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(1666, GOLD);
        BigDecimal expectedAmount = new BigDecimal("16.66");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToDonkeyValid() {
        BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(800, DONKEY);
        BigDecimal expectedAmount = new BigDecimal("2.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToDonkeyRoundingValid() {
        BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(3333, DONKEY);
        BigDecimal expectedAmount = new BigDecimal("8.33");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToCowValid() {
        BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(2400, COW);
        BigDecimal expectedAmount = new BigDecimal("3.00");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }

    @Test
    public void exchangeToCowRoundingValid() {
        BigDecimal exchangedCurrency = currencyExchanger.exchangeCurrency(12489, COW);
        BigDecimal expectedAmount = new BigDecimal("15.61");

        Assertions.assertEquals(expectedAmount, exchangedCurrency);
    }
}
