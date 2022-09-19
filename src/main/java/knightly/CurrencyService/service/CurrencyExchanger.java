package knightly.CurrencyService.service;

import knightly.CurrencyService.enums.Currency;

import java.math.BigDecimal;

public interface CurrencyExchanger {
    public BigDecimal exchangeCurrency(int enteredAmound, Currency requestedCurrency);
}
