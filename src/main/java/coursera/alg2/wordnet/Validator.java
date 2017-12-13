package coursera.alg2.wordnet;

class Validator {
    static void notNull(Object arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Arg should be not null");
        }
    }

    static void assertTrue(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("Condition is not met");
        }
    }
}
