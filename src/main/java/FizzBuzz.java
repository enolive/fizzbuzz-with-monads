import io.vavr.Predicates;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.text.MessageFormat;
import java.util.function.Predicate;

class FizzBuzz {

    static final int UPPER_LIMIT = 10000;
    private static final List<Tuple2<Predicate<Integer>, String>> RULES = List.of(
            Tuple.of(numbersDivisibleBy(3), "Fizz"),
            Tuple.of(numbersDivisibleBy(5), "Buzz")
    );

    static Either<String, Seq<String>> sequenceUpTo(int limit) {
        return Either.sequenceRight(Stream.rangeClosedBy(limit, 1, -1)
                                          .map(FizzBuzz::convert))
                     .map(Seq::reverse);
    }

    static Either<String, String> convert(int input) {
        return validate(input).map(FizzBuzz::convertRight);
    }

    private static Either<String, Integer> validate(int input) {
        return Either.<String, Integer>right(input)
                .filter(i -> i > 0)
                .getOrElse(Either.left("Input must be greater than zero."))
                .filter(i -> i <= UPPER_LIMIT)
                .getOrElse(Either.left(MessageFormat.format("Input must not exceed {0}.", UPPER_LIMIT)));
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
