class Rule {
    private final int divisor;
    private final String result;

    Rule(int divisor, String result) {
        this.divisor = divisor;
        this.result = result;
    }

    String getResult() {
        return result;
    }

    boolean appliesTo(int input) {
        return input % divisor == 0;
    }
}
