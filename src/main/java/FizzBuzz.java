import io.vavr.collection.List;
import io.vavr.control.Either;

class FizzBuzz {
    Either<String, String> tryCalculateSingle(int input) {
        return Either.right(calculateSingle(input));
    }

    private String calculateSingle(int input) {
        final var rules = List.of(
                new Rule(3, "Fizz"),
                new Rule(5, "Buzz"),
                new Rule(15, "Fizz-Buzz")
        );
        return rules.filter(rule -> rule.appliesTo(input))
                    .map(Rule::getResult)
                    .headOption()
                    .getOrElse(() -> String.valueOf(input));
    }

}
