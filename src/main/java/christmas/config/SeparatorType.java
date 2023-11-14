package christmas.config;

public enum SeparatorType {
    COMMA(","),
    HYPHEN("-"),
    LINE(System.lineSeparator());

    private final String symbol;

    SeparatorType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
