package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("할인 금액이 5000원 미만이면 뱃지는 없다.")
    @Test
    void 뱃지_없음() {
        //given
        final int discount = 2000;

        //when
        Badge badge = Badge.getBadgeByDiscount(discount);

        //then
        assertThat(badge).isEqualTo(Badge.NONE);
    }


    @DisplayName("할인 금액이 5000원 이상, 10000원 미만이면 뱃지는 별이다..")
    @Test
    void 뱃지_별() {
        //given
        final int discount = 7777;

        //when
        Badge badge = Badge.getBadgeByDiscount(discount);

        //then
        assertThat(badge).isEqualTo(Badge.STAR);
    }

    @DisplayName("할인 금액이 10000원 이상, 20000원 미만이면 뱃지는 별이다..")
    @Test
    void 뱃지_트리() {
        //given
        final int discount = 11111;

        //when
        Badge badge = Badge.getBadgeByDiscount(discount);

        //then
        assertThat(badge).isEqualTo(Badge.TREE);
    }

    @DisplayName("할인 금액이 20000원 이상이면 뱃지는 산타이다.")
    @Test
    void 뱃지_산타() {
        //given
        final int discount = 22222;

        //when
        Badge badge = Badge.getBadgeByDiscount(discount);

        //then
        assertThat(badge).isEqualTo(Badge.SANTA);
    }
}
