import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;

class FizzBuzz {

    private final List<Rule> rules = List.of(
            new Rule(3, "Fizz"),
            new Rule(5, "Buzz")
    );

    Either<String, String> tryCalculateSingle(int input) {
        return Either.right(calculateSingle(input));
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
