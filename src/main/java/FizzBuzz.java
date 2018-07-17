import io.vavr.control.Either;
import io.vavr.control.Option;

class FizzBuzz {
    static Either<String, String> calculate(int input) {
        return Option.of(input)
                     .filter(FizzBuzz::validInput)
                     .map(FizzBuzz::calculateRight)
                     .toEither("Input should be positive");
    }

    private static boolean validInput(int input) {
        return input > 0;
    }

    private static String calculateRight(int input) {
        return String.valueOf(input);
    }
}
