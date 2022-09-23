package knightly.CurrencyService.server;

import com.google.gson.Gson;
import knightly.CurrencyService.enums.Currency;
import knightly.CurrencyService.server.dto.CurrencyReply;
import knightly.CurrencyService.server.dto.CurrencyRequest;
import knightly.CurrencyService.service.CurrencyExchanger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServerTest {

    @InjectMocks
    CurrencyServer currencyServer;
    @Mock
    CurrencyExchanger currencyExchanger;

    @Test
    public void handleCurrencyRequest() {
        String currencyRequestJson = getCurrencyRequest();

        currencyServer.handleCurrencyRequest(currencyRequestJson);

        verify(currencyExchanger, times(1)).exchangeCurrency(any(Integer.class), any(Currency.class));
    }

    @Test
    public void handleFaultyCurrencyRequest() {
        String faultyCurrencyRequest = "this is not a CurrencyRequest, fool";

        currencyServer.handleCurrencyRequest(faultyCurrencyRequest);

        verifyNoInteractions(currencyExchanger);
    }

    @Test void handleWrongJsonObject() {
        String wrongDTOJson = getWrongDTOJsoN();

        currencyServer.handleCurrencyRequest(wrongDTOJson);

        verifyNoInteractions(currencyExchanger);
    }

    private String getWrongDTOJsoN() {
        return new Gson().toJson(new CurrencyReply(new BigDecimal(1)), CurrencyReply.class);
    }

    public String getCurrencyRequest() {
        return new Gson().toJson(new CurrencyRequest(499, Currency.cow), CurrencyRequest.class);
    }
}
