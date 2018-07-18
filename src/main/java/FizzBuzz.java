import io.vavr.Predicates;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.function.Predicate;

import static io.vavr.API.Tuple;

class FizzBuzz {
    private static final List<Tuple2<Predicate<Integer>, String>> RULES = List.of(
            Tuple(numbersDivisibleBy(3), "Fizz"),
            Tuple(numbersDivisibleBy(5), "Buzz")
    );

    static Either<String, Seq<String>> sequence(int limit) {
        return Either.sequenceRight(Stream.rangeClosedBy(limit, 1, -1)
                                          .map(FizzBuzz::calculate))
                     .map(Seq::reverse);
    }

    private static Either<String, Integer> toRight(int number) {
        return Either.right(number);
    }

    static Either<String, String> calculate(int input) {
        return validate(input).map(FizzBuzz::calculateRight);
    }

    private static Either<String, Integer> validate(int input) {
        return toRight(input).filter(i -> i > 0)
                             .getOrElse(Either.left("Input should be positive"))
                             .filter(i -> i <= 10000)
                             .getOrElse(Either.left("Input must be less or equal 10000"));
    }

    private static String calculateRight(int input) {
        return Option.some(getResultFromRules(input))
                     .filter(Predicates.noneOf(String::isEmpty))
                     .getOrElse(() -> String.valueOf(input));
    }

    private static String getResultFromRules(int input) {
        return RULES.filter(rule -> rule._1.test(input))
                    .map(rule -> rule._2)
                    .mkString("-");
    }

    private static Predicate<Integer> numbersDivisibleBy(int divisor) {
        return input -> input % divisor == 0;
    }

}
