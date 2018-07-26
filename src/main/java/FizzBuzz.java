import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Either;

class FizzBuzz {
    Either<String, String> tryCalculateSingle(int input) {
        return Either.right(calculateSingle(input));
    }

    private String calculateSingle(int input) {
        final var rules = List.of(
                Tuple.of(3, "Fizz"),
                Tuple.of(5, "Buzz")
        );
        return rules.filter(rule -> appliesTo(rule, input))
                    .map(i -> i._2)
                    .headOption()
                    .getOrElse(() -> String.valueOf(input));
    }

    private boolean appliesTo(Tuple2<Integer, String> rule, int input) {
        return isDivisibleBy(input, rule._1);
    }

    private boolean isDivisibleBy(int input, int divisor) {
        return input % divisor == 0;
    }
}
