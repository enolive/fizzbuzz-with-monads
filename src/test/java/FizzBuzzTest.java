import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuzzTest {
    @Test
    void normalNumbers() {
        assertThat(FizzBuzz.convert(1)).isEqualTo("1");
    }

}
