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
            return calculateDiscountByDday() + calculateWeekendDiscount() + calculateWeekdayDiscount()
                    + calculateBenefitByPresent() + calculateDiscountBySpecialDay();
        }
        return 0;
    }

    public int calculateFinalMoney() {
        return menus.calculateTotalMoney() - calculateTotalDiscount();
    }

    public int calculateDiscountByDday() {
        if (eventDay.calculateDayFromDecember() < 25) {
            return 1000 + eventDay.calculateDayFromDecember() * 100;
        }
        return 0;
    }

    public int calculateBenefitByPresent() {
        if (menus.canPresent()) {
            return 25000;
        }
        return 0;
    }

    public int calculateWeekendDiscount() {
        if (eventDay.isWeekend()) {
            return menus.getMainMenuCount() * 2023;
        }
        return 0;
    }

    public int calculateWeekdayDiscount() {
        if (!eventDay.isWeekend()) {
            return menus.getDessertMenuCount() * 2023;
        }
        return 0;
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

    private int calculateTotalDiscount() {
        return calculateWeekendDiscount() + calculateWeekdayDiscount() + calculateDiscountBySpecialDay()
                + calculateDiscountByDday();
    }
}
