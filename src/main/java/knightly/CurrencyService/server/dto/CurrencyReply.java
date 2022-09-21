package knightly.CurrencyService.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyReply {
    public BigDecimal calculatedCurrency;
}
