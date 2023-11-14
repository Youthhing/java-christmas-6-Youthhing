package christmas.model;

public class Discount {

    private final Menus menus;
    private final EventDay eventDay;

    public Discount(Menus menus, EventDay eventDay) {
        this.menus = menus;
        this.eventDay = eventDay;
    }

    public int calculateTotalBenefitPrice() {
        if (canDiscount()) {
            return calculateDiscountByDday() + calculateDiscountByDayType() + calculateDiscountByPresent()
                    + calculateDiscountBySpecialDay();
        }
        return 0;
    }

    public int calculateFinalMoney() {
        return menus.calculateTotalMoney() - calculateTotalDiscount();
    }

    public int calculateDiscountByDday() {
        if (eventDay.calculateDayToChristmas() >= 0) {
            return 1000 + getDaysFromDecember() * 100;
        }
        return 0;
    }

    public int calculateDiscountByPresent() {
        if (menus.canPresent()) {
            return 25000;
        }
        return 0;
    }

    public int calculateDiscountByDayType() {
        if (eventDay.isWeekend()) {
            return menus.getDessertMenuCount() * 2023;
        }
        return menus.getMainMenuCount() * 2023;
    }

    public int calculateDiscountBySpecialDay() {
        if (eventDay.isSpecialDay()) {
            return 1000;
        }
        return 0;
    }

    private boolean canDiscount() {
        return menus.calculateTotalMoney() >= 10000;
    }

    private int getDaysFromDecember() {
        return 24 - eventDay.calculateDayToChristmas();
    }

    private int calculateTotalDiscount() {
        return calculateDiscountByDayType() + calculateDiscountBySpecialDay() + calculateDiscountByDday();
    }
}
