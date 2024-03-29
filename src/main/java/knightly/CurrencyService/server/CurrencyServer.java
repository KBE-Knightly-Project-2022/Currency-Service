package knightly.CurrencyService.server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import knightly.CurrencyService.enums.Currency;
import knightly.CurrencyService.server.dto.CurrencyReply;
import knightly.CurrencyService.server.dto.CurrencyRequest;
import knightly.CurrencyService.service.CurrencyExchanger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


public class CurrencyServer {

    @Autowired
    CurrencyExchanger currencyExchangerImpl;
    private static final Logger logger = LoggerFactory.getLogger(CurrencyServer.class);

    @RabbitListener(queues = "${currency.queue.name}")
    public String handleCurrencyRequest(String currencyRequestString) {

        Currency requestedCurrency;
        int enteredAmount;
        try {
            CurrencyRequest currencyRequest = convertJsonToCurrencyRequest(currencyRequestString);
            enteredAmount = currencyRequest.getEnteredAmount();
            requestedCurrency = currencyRequest.getRequestedCurrency();
            logger.info("Got Currency Request with: " + enteredAmount + " " + requestedCurrency.toString());
        } catch (NumberFormatException | NullPointerException | JsonSyntaxException e) {
            logger.error("Error while unpacking request in class:" + this.getClass());
            return createErrorCurrencyReply();
        }
        try {
            BigDecimal exchangedCurrency = currencyExchangerImpl.exchangeCurrency(enteredAmount, requestedCurrency);
            logger.info("returning :" + exchangedCurrency);
            CurrencyReply currencyReply = new CurrencyReply(exchangedCurrency);
            return convertCurrencyReplyToJson(currencyReply);
        } catch (IllegalStateException e) {
            logger.error("Unkown currency, illegal State exception");
            return createErrorCurrencyReply();
        }
    }

    public String convertCurrencyReplyToJson(CurrencyReply currencyReply) {
        return new Gson().toJson(currencyReply);
    }

    private CurrencyRequest convertJsonToCurrencyRequest(String json) {
        return new Gson().fromJson(json, CurrencyRequest.class);
    }

    private String createErrorCurrencyReply() {
        return new Gson().toJson(new CurrencyReply(new BigDecimal("0.00")));
    }

}
