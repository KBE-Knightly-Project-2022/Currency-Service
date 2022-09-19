package knightly.CurrencyService.server.dto;

import knightly.CurrencyService.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyRequest {

    public int enteredAmount;
    public Currency requestedCurrency;
}
