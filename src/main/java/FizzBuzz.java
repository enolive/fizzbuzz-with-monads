import io.vavr.control.Either;
import io.vavr.control.Option;

class FizzBuzz {
    static Either<String, String> convert(int input) {
        return Either.right(convertRight(input));
    }

    private static String convertRight(int input) {
        return Option.some(input)
                     .filter(i -> input == 3)
                     .map(__ -> "Fizz")
                     .getOrElse(String.valueOf(input));
    }
}
