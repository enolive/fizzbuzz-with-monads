import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;

class FizzBuzz {

    private final List<DivByRule> rules = List.of(
            new DivByRule(3, "Fizz"),
            new DivByRule(5, "Buzz")
    );

    Either<ValidationError, Seq<String>> calculateSequence(int limit) {
        return Either.sequenceRight(Stream.rangeClosed(1, limit).map(this::calculateSingle));
    }

    Either<ValidationError, String> calculateSingle(int input) {
        return validate(input).map(this::singleValue);
    }

    private Either<ValidationError, Integer> validate(int input) {
        return Either
                .<ValidationError, Integer>right(input)
                .filter(i -> i > 0)
                .getOrElse(() -> Either.left(new ValidationError("input must be greater than zero.")))
                .filter(i -> i < 10000)
                .getOrElse(() -> Either.left(new ValidationError("input must be smaller than 10000.")));
    }

    private String singleValue(int input) {
        return Option.of(rules.filter(r -> r.appliesTo(input))
                              .map(r -> r.result)
                              .mkString("-"))
                     .filter(i -> !i.isEmpty())
                     .getOrElse(() -> String.valueOf(input));
    }

    private class DivByRule {
        private final int divisor;
        private final String result;

        DivByRule(int divisor, String result) {
            this.divisor = divisor;
            this.result = result;
        }

        boolean appliesTo(int input) {
            return input % divisor == 0;
        }
    }
}
