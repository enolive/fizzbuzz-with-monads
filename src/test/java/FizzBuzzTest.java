import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1234})
    void invalid_input_should_produce_an_error(int input) {
        assertThat(FizzBuzz.calculate(input).left().get()).isEqualTo("Input should be positive");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    void normal_numbers_should_be_returned_as_is(int input) {
        assertThat(FizzBuzz.calculate(input).right().get()).isEqualTo(String.valueOf(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9})
    void numbers_divisible_by_3_should_be_returned_as_fizz(int input) {
        assertThat(FizzBuzz.calculate(input).right().get()).isEqualTo("Fizz");
    }
}
