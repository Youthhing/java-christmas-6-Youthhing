package christmas.model;

import java.util.Arrays;

public enum Badge {
    NONE(0, 5000, "없음"),
    STAR(5000, 10000, "별"),
    TREE(10000, 20000, "트리"),
    SANTA(20000, Integer.MAX_VALUE, "산타");

    private final int minPrice;
    private final int maxPrice;
    private final String name;

    Badge(int minPrice, int maxPrice, String name) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.name = name;
    }

    public static Badge getBadgeByDiscount(int discount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.isInRange(discount))
                .findAny()
                .orElse(NONE);

    }

    private boolean isInRange(int discount) {
        return minPrice <= discount && discount < maxPrice;
    }

    @Override
    public String toString() {
        return name;
    }
}
