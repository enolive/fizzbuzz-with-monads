import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;

class FizzBuzz {

    private final List<DivByRule> rules = List.of(
            new DivByRule(numbersDivisibleBy(3), "Fizz"),
            new DivByRule(numbersDivisibleBy(5), "Buzz")
    );

    Either<ValidationError, Seq<String>> calculateSequence(int limit) {
        return Either.sequenceRight(Stream.rangeClosed(1, limit).map(this::calculateSingle));
    }

    Either<ValidationError, String> calculateSingle(int input) {
        return validate(input).map(this::singleValue);
    }

    private Function1<Integer, Boolean> numbersDivisibleBy(int divisor) {
        return Function2.of(this::isDivisibleBy).reversed().apply(divisor);
    }

    private boolean isDivisibleBy(int input, int divisor) {
        return input % divisor == 0;
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
                              .map(DivByRule::getResult)
                              .mkString("-"))
                     .filter(i -> !i.isEmpty())
                     .getOrElse(() -> String.valueOf(input));
    }

    @AllArgsConstructor
    private class DivByRule {
        private final Function1<Integer, Boolean> predicate;
        @Getter
        private final String result;

        boolean appliesTo(int input) {
            return predicate.apply(input);
        }
    }
}
