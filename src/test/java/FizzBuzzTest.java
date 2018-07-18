import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    void normalNumbers(int input) {
        assertThat(FizzBuzz.convert(input).get()).isEqualTo(String.valueOf(input));
    }

    @Test
    void fizz() {
        assertThat(FizzBuzz.convert(3).get()).isEqualTo("Fizz");
    }
}
