import io.vavr.control.Either;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1234})
    void invalid_input_should_produce_an_error(int input) {
        assertThat(calculate(input).left().get()).isEqualTo("Input should be positive");
    }

    private Either<String, String> calculate(int input) {
        return Either.left("Input should be positive");
    }
}
