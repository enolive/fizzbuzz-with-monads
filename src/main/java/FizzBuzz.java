import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.text.MessageFormat;

class FizzBuzz {

    static final int UPPER_LIMIT = 10000000;

    private final List<Rule> rules = List.of(
            new Rule(3, "Fizz"),
            new Rule(5, "Buzz")
    );

    Either<String, Seq<String>> tryCalculateSequenceUpTo(int limitClosed) {
        return Either.sequenceRight(Stream.rangeClosedBy(limitClosed, 1, -1)
                                          .map(this::tryCalculateSingle))
                     .map(Seq::reverse);
    }

    Either<String, String> tryCalculateSingle(int input) {
        return validate(input).map(this::calculateSingle);
    }

    private Either<String, Integer> validate(int input) {
        return Either.<String, Integer>right(input)
                .filter(i -> i > 0)
                .getOrElse(() -> Either.left("Input must be greater than zero."))
                .filter(i -> i <= UPPER_LIMIT)
                .getOrElse(() -> Either.left(MessageFormat.format("Input must be lower or equal {0}.", UPPER_LIMIT)));
    }

    private String calculateSingle(int input) {
        return Option.of(resultFromRulesFor(input))
                     .filter(this::resultNotEmpty)
                     .getOrElse(() -> String.valueOf(input));
    }

    private String resultFromRulesFor(int input) {
        return rules.filter(rule -> rule.appliesTo(input))
                    .map(Rule::getResult)
                    .mkString("-");
    }

    private boolean resultNotEmpty(String value) {
        return !value.isEmpty();
    }
}
