package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventDayTest {

    @DisplayName("날짜가 1 미만이면 예외가 발생한다.")
    @Test
    void 날짜_범위_미만_예외_처리() {
        //given
        final int date = -24;

        //when,then
        assertThatThrownBy(() -> new EventDay(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DATE_RANGE_ERROR.getMessage());
    }

    @DisplayName("날짜가 31 초과이면 예외가 발생한다.")
    @Test
    void 날짜_범위_초과_예외_처리() {
        //given
        final int date = 32;

        //when,then
        assertThatThrownBy(() -> new EventDay(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DATE_RANGE_ERROR.getMessage());
    }

    @DisplayName("날짜가 일요일이면 특별할인 날이다.")
    @Test
    void 날짜_일요일_특별할인날_반환() {
        //given
        final int date = 3;
        EventDay eventDay = new EventDay(date);

        //when
        boolean specialDay = eventDay.isSpecialDay();

        //then
        assertTrue(specialDay);
    }

    @DisplayName("크리스마스 당일이면 특별할인 날이다.")
    @Test
    void 날짜_크리스마스_당일_특별할인날_반환() {
        //given
        final int date = 25;
        EventDay eventDay = new EventDay(date);

        //when
        boolean specialDay = eventDay.isSpecialDay();

        //then
        assertTrue(specialDay);
    }

    @DisplayName("크리스마스 당일이 아니고 일요일이 아니면 특별할인 날이 아니다.")
    @Test
    void 날짜_특별할인_아닌_경우() {
        //given
        final int date = 1;
        EventDay eventDay = new EventDay(date);

        //when
        boolean specialDay = eventDay.isSpecialDay();

        //then
        assertFalse(specialDay);
    }

    @DisplayName("주어진 날짜가 월요일이면 false를 반환한다.")
    @Test
    void 월요일_평일_구분_기능() {
        //given
        final int date = 4;
        EventDay eventDay = new EventDay(date);

        //when
        boolean weekend = eventDay.isWeekend();

        //then
        assertFalse(weekend);
    }

    @DisplayName("주어진 날짜가 금요일이면 true를 반환한다.")
    @Test
    void 금요일_구분_기능() {
        //given
        final int date = 1;
        EventDay eventDay = new EventDay(date);

        //when
        boolean weekend = eventDay.isWeekend();

        //then
        assertTrue(weekend);
    }

    @DisplayName("주어진 날짜가 토요일이면 true를 반환한다.")
    @Test
    void 토요일_구분_기능() {
        //given
        final int date = 2;
        EventDay eventDay = new EventDay(date);

        //when
        boolean weekend = eventDay.isWeekend();

        //then
        assertTrue(weekend);
    }

    @DisplayName("크리스마스까지의 남은 디데이를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25})
    void 디데이_계산_기능(int today) {
        //given
        EventDay eventDay = new EventDay(today);

        //when
        int remainDays = eventDay.calculateDayToChristmas();

        //when
        assertThat(remainDays).isEqualTo(25 - today);
    }

}
