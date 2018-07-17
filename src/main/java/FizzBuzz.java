import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.function.Predicate;

import static io.vavr.API.Tuple;

class FizzBuzz {

    private static final List<Tuple2<Predicate<Integer>, String>> RULES = List.of(
            Tuple(isDivisibleBy(3), "Fizz"),
            Tuple(isDivisibleBy(5), "Buzz")
    );

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
        return Option.some(getResultFromRules(input))
                     .filter(result -> !result.isEmpty())
                     .getOrElse(() -> String.valueOf(input));
    }

    private static String getResultFromRules(int input) {
        return RULES.filter(rule -> rule._1.test(input))
                    .map(rule -> rule._2)
                    .mkString("-");
    }

    private static Predicate<Integer> isDivisibleBy(int divisor) {
        return input -> input % divisor == 0;
    }
}
