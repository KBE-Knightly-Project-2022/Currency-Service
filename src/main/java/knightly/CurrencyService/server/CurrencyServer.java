package knightly.CurrencyService.server;

import knightly.CurrencyService.service.CurrencyExchanger;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.math.BigDecimal;


public class CurrencyServer {

    private final String ENTERED_AMOUNT = "enteredAmount";
    private int enteredAmount = 0;
    private final String REQUESTED_CURRENCY = "requestedCurrency";
    private final CurrencyExchanger currencyExchanger = new CurrencyExchanger();

    @RabbitListener(queues = "${queue.name}")
    public BigDecimal calculateCurrency(JSONObject message) {

        String requestedCurrency = "";
        try {
            enteredAmount = (int) getIntFromPayload(message);
            requestedCurrency = (String) getStringFromPayload(message);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return currencyExchanger.exchangeCurrency(enteredAmount, requestedCurrency);
    }

    private Object getIntFromPayload(JSONObject payload) {
        return payload.get(ENTERED_AMOUNT);
    }

    private Object getStringFromPayload(JSONObject payload) {
        return payload.get(REQUESTED_CURRENCY);
    }

}
