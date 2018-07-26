import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}
