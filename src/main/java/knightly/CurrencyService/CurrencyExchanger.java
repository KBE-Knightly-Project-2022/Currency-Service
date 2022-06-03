package knightly.CurrencyService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyExchanger {

    public static BigDecimal exchangeCurrency(int enteredAmount, String requestedCurrency) {
        return switch (requestedCurrency) {
            case "silver" ->  convertToSilver(enteredAmount);
            case "gold" -> convertToGold(enteredAmount);
            case "donkey" -> convertToDonkey(enteredAmount);
            case "cow" -> convertToCow(enteredAmount);
            default -> throw new IllegalStateException("Unexpected value: " + requestedCurrency);
        };
    }

    private static BigDecimal convertToSilver(int copper) {
        BigDecimal copperDecimal = new BigDecimal(copper);
        BigDecimal silverDecimal = new BigDecimal(10);
        BigDecimal divided = copperDecimal.divide(silverDecimal);

        return roundBigDecimal(divided);
    }

    private static BigDecimal convertToGold(int copper) {
        BigDecimal copperDecimal = new BigDecimal(copper);
        BigDecimal goldDecimal = new BigDecimal(100);
        BigDecimal divided = copperDecimal.divide(goldDecimal);

        return roundBigDecimal(divided);
    }

    private static BigDecimal convertToDonkey(int copper) {
        BigDecimal copperDecimal = new BigDecimal(copper);
        BigDecimal donkeyDecimal = new BigDecimal(400);
        BigDecimal divided = copperDecimal.divide(donkeyDecimal);

        return roundBigDecimal(divided);
    }

    private static BigDecimal convertToCow(int copper) {
        BigDecimal copperDecimal = new BigDecimal(copper);
        BigDecimal cowDecimal = new BigDecimal(800);
        BigDecimal divided = copperDecimal.divide(cowDecimal);
        return roundBigDecimal(divided);
    }

    private static BigDecimal roundBigDecimal(BigDecimal toRound){
        return toRound.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
