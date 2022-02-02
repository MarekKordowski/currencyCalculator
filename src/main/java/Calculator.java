import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
public class Calculator {

    private final Map<String, Double> exchangeValues = new HashMap<>();
    private List<CurrencyRate> currRateList;


    public Double convert(Double amount, String shortcut) {
        XmlParse xmlParse = new XmlParse();
        currRateList = xmlParse.getCurrRateList();

        convertListToMap(currRateList);

        double price;
        if (!exchangeValues.containsKey(shortcut)) {
            throw new IllegalArgumentException("This currency doesn't exist in our database");
        } else
            price = amount * exchangeValues.get(shortcut);
        return round(price);
    }

    private void convertListToMap(List<CurrencyRate> currencyRates) {
        for (CurrencyRate currencyRate : currencyRates) {
            exchangeValues.put(currencyRate.getCurrency(), Double.parseDouble(currencyRate.getRate()));
        }
    }

    private double round(Double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return exchangeValues.equals(that.exchangeValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeValues);
    }
}