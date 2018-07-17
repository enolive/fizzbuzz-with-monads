import io.vavr.control.Either;
import io.vavr.control.Option;

import static io.vavr.API.*;

class FizzBuzz {
    static Either<String, String> calculate(int input) {
        return Option.of(input)
                     .filter(FizzBuzz::validInput)
                     .map(FizzBuzz::calculateRight)
                     .toEither("Input should be positive");
    }

    private static boolean validInput(int input) {
        return input > 0;
    }

    private static String calculateRight(int input) {
        return Match(input)
                .option(
                        Case($(i -> isDivisibleBy(i, 3)), "Fizz"),
                        Case($(i -> isDivisibleBy(i, 5)), "Buzz")
                )
                .getOrElse(() -> String.valueOf(input));
    }

    private static boolean isDivisibleBy(int input, int divisor) {
        return input % divisor == 0;
    }
}
