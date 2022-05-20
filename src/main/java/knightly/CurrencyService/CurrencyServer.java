package knightly.CurrencyService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class CurrencyServer {

    private int enteredAmount = 0;
    private String requestedCurrency = "";

    @RabbitListener(queues = "${queue.name}")
    public float calculateCurrency(String message) {
        try {
            enteredAmount = Integer.parseInt(message.split(",")[0]);
            requestedCurrency = message.split(",")[1];
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return CurrencyExchanger.exchangeCurrency(enteredAmount, requestedCurrency);
    }

}
