import java.util.Objects;

class ValidationError {
    private String message;

    ValidationError(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationError that = (ValidationError) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "message='" + message + '\'' +
                '}';
    }
}
