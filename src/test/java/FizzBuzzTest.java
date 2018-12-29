import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {

    private FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Nested
    class SingleValue {
        @ParameterizedTest
        @CsvSource(value = {
                "1, 1",
                "2, 2",
                "4, 4",
        })
        void normal_numbers_are_replicated(int input, String expected) {
            assertThat(fizzBuzz.calculateSingle(input)).isEqualTo(Either.right(expected));
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 6, 9})
        void numbers__divisible_by_3_are_converted_to_fizz(int input) {
            assertThat(fizzBuzz.calculateSingle(input)).isEqualTo(Either.right("Fizz"));
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 10, 20})
        void numbers__divisible_by_5_are_converted_to_buzz(int input) {
            assertThat(fizzBuzz.calculateSingle(input)).isEqualTo(Either.right("Buzz"));
        }

        @ParameterizedTest
        @ValueSource(ints = {15, 30, 45})
        void numbers__divisible_by_3_and_5_are_converted_to_fizz_buzz(int input) {
            assertThat(fizzBuzz.calculateSingle(input)).isEqualTo(Either.right("Fizz-Buzz"));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -1000})
        void small_input_is_rejected(int input) {
            assertThat(fizzBuzz.calculateSingle(input)).isEqualTo(
                    Either.left(new ValidationError("input must be greater than zero.")));
        }

        @ParameterizedTest
        @ValueSource(ints = {10000})
        void large_input_is_rejected(int input) {
            assertThat(fizzBuzz.calculateSingle(input)).isEqualTo(
                    Either.left(new ValidationError("input must be smaller than 10000.")));
        }
    }

    @Nested
    class Sequence {
        @Test
        void single_value() {
            assertThat(fizzBuzz.calculateSequence(1).get()).containsExactly("1");
        }

        @Test
        void multiple_values() {
            assertThat(fizzBuzz.calculateSequence(15).get()).containsExactly(
                    "1", "2", "Fizz", "4", "Buzz", "Fizz",
                    "7", "8", "Fizz", "Buzz", "11", "Fizz",
                    "13", "14", "Fizz-Buzz");
        }

        @Test
        void empty_sequence() {
            assertThat(fizzBuzz.calculateSequence(0).get()).isEmpty();
        }

        @Test
        void validation_errors() {
            assertThat(fizzBuzz.calculateSequence(10000)).isEqualTo(
                    Either.left(new ValidationError("input must be smaller than 10000.")));
        }
    }
}
