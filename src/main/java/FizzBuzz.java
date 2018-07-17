import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.function.Predicate;

import static io.vavr.API.Tuple;

class FizzBuzz {
    private static final List<Tuple2<Predicate<Integer>, String>> RULES = List.of(
            Tuple(isDivisibleBy(3), "Fizz"),
            Tuple(isDivisibleBy(5), "Buzz")
    );

    static Either<String, Stream<String>> sequence(int limit) {
        final Stream<String> sequence = Stream.rangeClosed(1, limit)
                                              .map(FizzBuzz::calculateRight);
        return Either.right(sequence);
    }

    static Either<String, String> calculate(int input) {
        return validate(input).right()
                              .map(FizzBuzz::calculateRight)
                              .toEither();
    }

    private static Either<String, Integer> validate(int input) {
        // TODO: this is a little bit awkward as VAVR's filter produces
        // an Option in its filter implementation
        // there is an open merge request targeting vavr-1.0.0
        // that targets this problem:
        // https://github.com/vavr-io/vavr/pull/2256
        return Either.<String, Integer>right(input)
                .filter(i -> i > 0)
                .getOrElse(Either.left("Input should be positive"))
                .filter(i -> i <= 10000)
                .getOrElse(Either.left("Input must be less or equal 10000"));
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
