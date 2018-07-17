import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
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
        return Option.of(input)
                     .filter(i -> i > 0)
                     .map(this::calculateRight)
                     .toEither("Input should be positive");
    }

    private String calculateRight(Integer obj) {
        return "1";
    }

    @Test
    void normal_numbers_should_be_returned_as_is() {
        assertThat(calculate(1).right().get()).isEqualTo("1");
    }
}
