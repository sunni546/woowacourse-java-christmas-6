package christmas.config;

public enum BadgeType {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int price;

    BadgeType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static BadgeType determineBadgeByPrice(double price) {
        if (price >= SANTA.price) {
            return SANTA;
        }
        if (price >= TREE.price) {
            return TREE;
        }
        if (price >= STAR.price) {
            return STAR;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
