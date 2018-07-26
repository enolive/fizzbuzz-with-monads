import io.vavr.control.Either;

class FizzBuzz {
    Either<String, String> calculateSingle(int input) {
        return Either.right(String.valueOf(input));
    }
}
