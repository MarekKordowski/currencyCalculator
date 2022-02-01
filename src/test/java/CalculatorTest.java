import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void shouldConvertEURCurrencyIntoGivenCurrency() {
        //given
        Double amount = 100d;
        String shortcut = "CHF";
        Double expected = 103.78d;

        //when
        Double convert = calculator.convert(amount, shortcut);

        //then
        assertEquals(expected, convert);
    }

    @Test
    void shouldThrowExceptionWhenCurrencyDoesNotExist() {

        //given
        Double amount = 100d;
        String shortcut = "aaa";

        // when
        Throwable throwable = catchThrowable(() -> calculator.convert(amount, shortcut));

        // then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("This currency doesn't exist in our database");
    }
}
