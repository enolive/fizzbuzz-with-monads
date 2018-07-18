import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @Nested
    @DisplayName("rules for converting a single digit")
    class ConversionRules {
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 4})
        @DisplayName("normal numbers should be kept as is")
        void normalNumbers(int input) {
            assertThat(FizzBuzz.convert(input).get()).isEqualTo(String.valueOf(input));
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 6, 9})
        @DisplayName("numbers divisible by 3 should be converted to Fizz")
        void fizz(int input) {
            assertThat(FizzBuzz.convert(input).get()).isEqualTo("Fizz");
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 10, 100})
        @DisplayName("numbers divisible by 5 should be converted to Buzz")
        void buzz(int input) {
            assertThat(FizzBuzz.convert(input).get()).isEqualTo("Buzz");
        }

        @ParameterizedTest
        @ValueSource(ints = {15, 30, 75})
        @DisplayName("numbers divisible by 3 and 5 should be converted to Fizz-Buzz")
        void fizzBuzz(int input) {
            assertThat(FizzBuzz.convert(input).get()).isEqualTo("Fizz-Buzz");
        }
    }

    @DisplayName("input validation")
    @Nested
    class Validation {
        @Test
        void notPositive() {
            assertThat(FizzBuzz.convert(0).getLeft()).isEqualTo("Input must be greater than zero.");
        }
    }
}
