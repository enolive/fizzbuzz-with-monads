import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @Test
    void normalNumbers() {
        final String result = FizzBuzz.convert(1).get();
        assertThat(result).isEqualTo("1");
    }

}
