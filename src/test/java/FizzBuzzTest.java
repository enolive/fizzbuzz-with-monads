import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    void normalNumbers(int input) {
        assertThat(fizzBuzz.tryCalculateSingle(input).get())
                .isEqualTo(String.valueOf(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6})
    void numbersDivisibleBy3(int input) {
        assertThat(fizzBuzz.tryCalculateSingle(input).get())
                .isEqualTo(String.valueOf("Fizz"));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10})
    void numbersDivisibleBy5(int input) {
        assertThat(fizzBuzz.tryCalculateSingle(input).get())
                .isEqualTo(String.valueOf("Buzz"));
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30})
    void numbersDivisibleBy3and5(int input) {
        assertThat(fizzBuzz.tryCalculateSingle(input).get())
                .isEqualTo(String.valueOf("Fizz-Buzz"));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void inputTooLow(int input) {
        assertThat(fizzBuzz.tryCalculateSingle(input).getLeft())
                .isEqualTo("Input must be greater than zero.");
    }

    @Test
    void inputTooHigh() {
        assertThat(fizzBuzz.tryCalculateSingle(FizzBuzz.UPPER_LIMIT + 1).getLeft())
                .isEqualTo(MessageFormat.format("Input must be lower or equal {0}.", FizzBuzz.UPPER_LIMIT));
    }

    @Test
    void sequence() {
        assertThat(fizzBuzz.tryCalculateSequenceUpTo(15).get())
                .containsExactly(
                        "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8",
                        "Fizz", "Buzz", "11", "Fizz", "13", "14", "Fizz-Buzz");
    }

    @Test
    void sequenceTooHigh() {
        assertThat(fizzBuzz.tryCalculateSequenceUpTo(FizzBuzz.UPPER_LIMIT + 1).getLeft())
                .isEqualTo(MessageFormat.format("Input must be lower or equal {0}.", FizzBuzz.UPPER_LIMIT));
    }
}
