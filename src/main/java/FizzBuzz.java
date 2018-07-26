import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

class FizzBuzz {

    private final List<Rule> rules = List.of(
            new Rule(3, "Fizz"),
            new Rule(5, "Buzz")
    );

    Either<String, String> tryCalculateSingle(int input) {
        return validate(input).map(this::calculateSingle);
    }

    private Either<String, Integer> validate(int input) {
        return Either.<String, Integer>right(input)
                .filter(i -> i > 0)
                .getOrElse(() -> Either.left("Input must be greater than zero."));
    }

    private String calculateSingle(int input) {
        return Option.of(calculateResultFromRules(input))
                     .filter(this::resultNotEmpty)
                     .getOrElse(() -> String.valueOf(input));
    }

    private String calculateResultFromRules(int input) {
        return rules.filter(rule -> rule.appliesTo(input))
                    .map(Rule::getResult)
                    .mkString("-");
    }

    private boolean resultNotEmpty(String r) {
        return !r.isEmpty();
    }

}
