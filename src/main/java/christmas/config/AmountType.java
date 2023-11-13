package christmas.config;

public enum AmountType {
    EVENT_THRESHOLD(10000),
    GIFT_THRESHOLD(120000),
    DEFAULT_DISCOUNT(1000),
    DECEMBER_DISCOUNT(2023),
    CHRISTMAS_INCREMENT(100);

    private final int amount;

    AmountType(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
