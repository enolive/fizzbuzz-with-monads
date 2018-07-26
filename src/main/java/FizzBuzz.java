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
                new Rule(3, "Fizz"),
                new Rule(5, "Buzz")
        );
        return rules.filter(rule -> appliesTo(rule, input))
                    .map(result -> result.result)
                    .headOption()
                    .getOrElse(() -> String.valueOf(input));
    }

    private boolean appliesTo(Rule rule, int input) {
        return isDivisibleBy(input, rule.divisor);
    }

    private boolean isDivisibleBy(int input, int divisor) {
        return input % divisor == 0;
    }
}
