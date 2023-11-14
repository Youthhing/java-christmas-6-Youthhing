package christmas.model;

import christmas.constant.ErrorMessage;
import java.time.LocalDate;

public class EventDay {

    private final LocalDate date;

    public EventDay(int date) {
        validateRange(date);
        this.date = LocalDate.of(2023, 12, date);
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
