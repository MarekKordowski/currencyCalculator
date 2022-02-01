import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurrencyRate {

    private final String currency;
    private final String rate;
}
