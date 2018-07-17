import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.function.Predicate;

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
                        Case($(isDivisibleBy(3)), "Fizz"),
                        Case($(isDivisibleBy(5)), "Buzz")
                )
                .getOrElse(() -> String.valueOf(input));
    }

    private static Predicate<Integer> isDivisibleBy(int divisor) {
        return input -> input % divisor == 0;
    }
}
