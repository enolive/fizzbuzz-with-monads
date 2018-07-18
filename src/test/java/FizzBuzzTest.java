import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    @DisplayName("normal numbers should be kept as is")
    void normalNumbers(int input) {
        assertThat(FizzBuzz.convert(input).get()).isEqualTo(String.valueOf(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6})
    @DisplayName("numbers divisible by 3 should be converted to Fizz")
    void fizz(int input) {
        assertThat(FizzBuzz.convert(input).get()).isEqualTo("Fizz");
    }
}
