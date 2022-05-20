package knightly.CurrencyService;

public class CurrencyExchanger {

    public static float exchangeCurrency(int enteredAmount, String requestedCurrency) {
        return switch (requestedCurrency) {
            case "silver" -> convertToSilver(enteredAmount);
            case "gold" -> convertToGold(enteredAmount);
            case "donkey" -> convertToDonkey(enteredAmount);
            case "cow" -> convertToCow(enteredAmount);
            default -> throw new IllegalStateException("Unexpected value: " + requestedCurrency);
        };
    }

    private static float convertToSilver(int copper) {
        return ((float) copper) / 10;
    }

    private static float convertToGold(int copper) {
        return ((float) copper) / 100;
    }

    private static float convertToDonkey(int copper) {
        return ((float) copper) / 300;
    }

    private static float convertToCow(int copper) {
        return ((float) copper) / 900;
    }
}
