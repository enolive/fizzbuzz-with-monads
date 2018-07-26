import io.vavr.control.Either;
import io.vavr.control.Option;

class FizzBuzz {
    Either<String, String> tryCalculateSingle(int input) {
        return Either.right(calculateSingle(input));
    }

    private String calculateSingle(int input) {
        return Option.of(input)
                     .filter(i -> i == 3)
                     .map(i -> "Fizz")
                     .getOrElse(() -> String.valueOf(input));
    }
}
