package christmas.model;

import christmas.constant.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventDay {

    private final LocalDate date;

    public EventDay(int date) {
        validateRange(date);
        this.date = LocalDate.of(2023, 12, date);
    }

    public boolean isWeekend() {
        return isSaturday() || isFriDay();
    }

    public boolean isSpecialDay() {
        return isSunDay() || isChristMas();
    }

    private boolean isFriDay() {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    private boolean isChristMas() {
        return date.getDayOfMonth() == 25;
    }

    private boolean isSaturday() {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    private boolean isSunDay() {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private void validateRange(int date) {
        if (!isInRange(date)) {
            throw new IllegalArgumentException(ErrorMessage.DATE_RANGE_ERROR.getMessage());
        }
    }

    private boolean isInRange(int date) {
        return 1 <= date && date <= 31;
    }
}