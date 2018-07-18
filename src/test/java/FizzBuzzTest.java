import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;

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
        @ParameterizedTest
        @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
        @DisplayName("numbers smaller than 1 are not considered as countable and therefore rejected")
        void notPositive(int input) {
            assertThat(FizzBuzz.convert(input).getLeft()).isEqualTo("Input must be greater than zero.");
        }

        @ParameterizedTest
        @ValueSource(ints = {FizzBuzz.UPPER_LIMIT + 1, Integer.MAX_VALUE})
        @DisplayName("numbers larger than 1 will put a big strain on a number sequence and therefore will be rejected")
        void tooLarge(int input) {
            assertThat(FizzBuzz.convert(input).getLeft()).isEqualTo(MessageFormat.format("Input must not exceed {0}.", FizzBuzz.UPPER_LIMIT));
        }
    }

    @DisplayName("sequence generation")
    @Nested
    class Sequence {
        @Test
        @DisplayName("all possible distinct values resulting from the rules")
        void normal() {
            assertThat(FizzBuzz.sequenceUpTo(15).get()).containsExactly(
              "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz",
              "Buzz", "11", "Fizz", "13", "14", "Fizz-Buzz"
            );
        }

        @Test
        @DisplayName("large sequences should work as well")
        void large() {
            assertThat(FizzBuzz.sequenceUpTo(FizzBuzz.UPPER_LIMIT).get()).hasSize(FizzBuzz.UPPER_LIMIT);
        }
    }
}
