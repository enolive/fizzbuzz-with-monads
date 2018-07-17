import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1234})
    void small_input_should_produce_an_error(int input) {
        assertThat(FizzBuzz.calculate(input).left().get()).isEqualTo("Input should be positive");
    }

    @ParameterizedTest
    @ValueSource(ints = {10001, 34567})
    void large_input_should_produce_an_error(int input) {
        assertThat(FizzBuzz.calculate(input).left().get()).isEqualTo("Input must be less or equal 10000");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    void normal_numbers_should_be_returned_as_is(int input) {
        assertThat(FizzBuzz.calculate(input).get()).isEqualTo(String.valueOf(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9})
    void numbers_divisible_by_3_should_be_returned_as_fizz(int input) {
        assertThat(FizzBuzz.calculate(input).get()).isEqualTo("Fizz");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    void numbers_divisible_by_5_should_be_returned_as_buzz(int input) {
        assertThat(FizzBuzz.calculate(input).get()).isEqualTo("Buzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void numbers_divisible_by_3_and_5_should_be_returned_as_fizz_buzz(int input) {
        assertThat(FizzBuzz.calculate(input).get()).isEqualTo("Fizz-Buzz");
    }

    @Test
    void sequence_should_contain_the_expected_items() {
        assertThat(FizzBuzz.sequence(15).get()).containsExactly(
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz",
                "Buzz", "11", "Fizz", "13", "14", "Fizz-Buzz"
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10000})
    void sequence_with_limit_too_low_should_be_empty(int limit) {
        assertThat(FizzBuzz.sequence(limit).get()).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {10001, 99999})
    void sequence_with_limit_too_high_should_fail(int limit) {
        assertThat(FizzBuzz.sequence(limit).getLeft()).isEqualTo("Input must be less or equal 10000");
    }
}
