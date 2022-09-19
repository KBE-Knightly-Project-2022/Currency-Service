package knightly.CurrencyService.server;

import knightly.CurrencyService.enums.Currency;
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
    public BigDecimal calculateCurrency(CurrencyRequest currencyRequest) {

        Currency requestedCurrency = Currency.bronze;
        int enteredAmount = 0;
        try {
            enteredAmount = currencyRequest.getEnteredAmount();
            requestedCurrency = currencyRequest.getRequestedCurrency();
        } catch (NumberFormatException | NullPointerException e) {
            logger.error("Error while unpacking request in class:" + this.getClass());
        }
        try {
            return currencyExchangerImpl.exchangeCurrency(enteredAmount, requestedCurrency);
        } catch (IllegalStateException e) {
            logger.error("Unkown currency, illegal State exception");
            return new BigDecimal("0");
        }
    }

}
