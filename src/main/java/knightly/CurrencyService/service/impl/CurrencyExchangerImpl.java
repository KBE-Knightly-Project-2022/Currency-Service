package knightly.CurrencyService.service.impl;

import knightly.CurrencyService.enums.Currency;
import knightly.CurrencyService.service.CurrencyExchanger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyExchangerImpl implements CurrencyExchanger {
    private final BigDecimal silverDecimal = new BigDecimal(10);
    private final BigDecimal goldDecimal = new BigDecimal(100);
    private final BigDecimal donkeyDecimal = new BigDecimal(250);
    private final BigDecimal cowDecimal = new BigDecimal(500);

    @Override
    public BigDecimal exchangeCurrency(int enteredAmount, Currency requestedCurrency) {
        return switch (requestedCurrency) {
            case bronze -> convertToBronze(enteredAmount);
            case silver ->  convertToSilver(enteredAmount);
            case gold -> convertToGold(enteredAmount);
            case donkey -> convertToDonkey(enteredAmount);
            case cow -> convertToCow(enteredAmount);
            default -> throw new IllegalStateException("Unexpected value: " + requestedCurrency);
        };
    }
    
    private BigDecimal convertToBronze(int enteredAmount) {
        return new BigDecimal(enteredAmount);        
    }

    private BigDecimal convertToSilver(int enteredAmount) {
        BigDecimal enteredDecimal = new BigDecimal(enteredAmount);
        BigDecimal divided = enteredDecimal.divide(this.silverDecimal);

        return roundBigDecimal(divided);
    }

    private BigDecimal convertToGold(int enteredAmount) {
        BigDecimal enteredDecimal = new BigDecimal(enteredAmount);
        BigDecimal divided = enteredDecimal.divide(this.goldDecimal);

        return roundBigDecimal(divided);
    }

    private BigDecimal convertToDonkey(int enteredAmount) {
        BigDecimal enteredDecimal = new BigDecimal(enteredAmount);
        BigDecimal divided = enteredDecimal.divide(this.donkeyDecimal);

        return roundBigDecimal(divided);
    }

    private BigDecimal convertToCow(int enteredAmount) {
        BigDecimal enteredDecimal = new BigDecimal(enteredAmount);
        BigDecimal divided = enteredDecimal.divide(this.cowDecimal);

        return roundBigDecimal(divided);
    }

    private BigDecimal roundBigDecimal(BigDecimal toRound){
        return toRound.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
