import io.vavr.Predicates;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.function.Predicate;

class FizzBuzz {

    private static final List<Tuple2<Predicate<Integer>, String>> RULES = List.of(
            Tuple.of(numbersDivisibleBy(3), "Fizz"),
            Tuple.of(numbersDivisibleBy(5), "Buzz")
    );

    static Either<String, String> convert(int input) {
        return Either.right(convertRight(input));
    }

    private static String convertRight(int input) {
        return Option.some(calculateResultFromRulesFor(input))
                     .filter(Predicates.noneOf(String::isEmpty))
                     .getOrElse(() -> String.valueOf(input));
    }

    private static String calculateResultFromRulesFor(int input) {
        return RULES.filter(rule -> rule._1.test(input))
                    .map(rule -> rule._2)
                    .mkString("-");
    }

    private static Predicate<Integer> numbersDivisibleBy(int divisor) {
        return i -> i % divisor == 0;
    }
}
