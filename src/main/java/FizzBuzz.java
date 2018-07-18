import io.vavr.Function1;
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
        return validateLimit(limit).flatMap(FizzBuzz::eitherSequence);
    }

    private static Either<String, Integer> validateLimit(int limit) {
        return Function1.of(FizzBuzz::lessThanUpperLimit)
                        .compose(FizzBuzz::toRight)
                        .apply(limit);
    }

    private static Either<String, Seq<String>> eitherSequence(int limit) {
        return Either.sequenceRight(Stream.rangeClosed(1, limit)
                                          .map(FizzBuzz::calculate));
    }

    static Either<String, String> calculate(int input) {
        return validate(input).right()
                              .map(FizzBuzz::calculateRight)
                              .toEither();
    }

    private static Either<String, Integer> validate(int input) {
        return Function1.of(FizzBuzz::lessThanUpperLimit)
                        .compose(FizzBuzz::isPositive)
                        .compose(FizzBuzz::toRight)
                        .apply(input);
    }

    private static Either<String, Integer> toRight(int number) {
        return Either.right(number);
    }

    private static Either<String, Integer> lessThanUpperLimit(Either<String, Integer> right) {
        return right.filter(i -> i <= 10000)
                    .getOrElse(Either.left("Input must be less or equal 10000"));
    }

    private static Either<String, Integer> isPositive(Either<String, Integer> right) {
        return right.filter(i -> i > 0)
                    .getOrElse(Either.left("Input should be positive"));
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

    private static Predicate<Integer> numbersDivisibleBy(int divisor) {
        return input -> input % divisor == 0;
    }

}
