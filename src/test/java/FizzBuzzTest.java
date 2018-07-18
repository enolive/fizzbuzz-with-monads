import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    void normalNumbers(int input) {
        assertThat(FizzBuzz.convert(input).get()).isEqualTo(String.valueOf(input));
    }

}
