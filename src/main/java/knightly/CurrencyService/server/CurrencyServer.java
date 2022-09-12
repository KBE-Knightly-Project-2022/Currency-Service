package knightly.CurrencyService.server;

import knightly.CurrencyService.service.CurrencyExchanger;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


public class CurrencyServer {

    @Autowired
    CurrencyExchanger currencyExchanger;
    private final String ENTERED_AMOUNT = "enteredAmount";
    private int enteredAmount = 0;
    private final String REQUESTED_CURRENCY = "requestedCurrency";
    private static final Logger logger = LoggerFactory.getLogger(CurrencyServer.class);

    @RabbitListener(queues = "${currency.queue.name}")
    public BigDecimal calculateCurrency(JSONObject message) {

        String requestedCurrency = "bronze";
        enteredAmount = 0;
        try {
            enteredAmount = (int) getIntFromPayload(message);
            requestedCurrency = (String) getStringFromPayload(message);
        } catch (NumberFormatException e) {
            logger.error("Error while unpacking request in class:" + this.getClass());
        }
        try {
            return currencyExchanger.exchangeCurrency(enteredAmount, requestedCurrency);
        } catch (IllegalStateException e) {
            logger.error("Unkown currency, illegal State exception");
            return new BigDecimal("0");
        }
    }

    private Object getIntFromPayload(JSONObject payload) {
        return payload.get(ENTERED_AMOUNT);
    }

    private Object getStringFromPayload(JSONObject payload) {
        return payload.get(REQUESTED_CURRENCY);
    }

}
