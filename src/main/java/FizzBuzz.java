import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.function.Predicate;

class FizzBuzz {
    static Either<String, String> convert(int input) {
        return Either.right(convertRight(input));
    }

    private static String convertRight(int input) {
        return Option.some(input)
                     .filter(numbersDivisibleBy(3))
                     .map(__ -> "Fizz")
                     .getOrElse(String.valueOf(input));
    }

    private static Predicate<Integer> numbersDivisibleBy(int divisor) {
        return i -> i % divisor == 0;
    }
}
