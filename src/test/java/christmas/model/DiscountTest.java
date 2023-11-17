package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountTest {

    @DisplayName("별표시된 날의 특별할인 금액은 1000원이다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 특별할인_적용_금액_계산(final int date) {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.BARBEQUE_LIB, 1);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.CHAMPAGNE, 1);
        EventDay eventDay = new EventDay(date);
        Discount discount = new Discount(new Menus(order), eventDay);

        //when
        int specialDayDiscount = discount.calculateDiscountBySpecialDay();

        //then
        assertThat(specialDayDiscount).isEqualTo(1000);
    }

    @DisplayName("별표시가 되지 않은 날의 특별할인 금액은 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29})
    void 특별할인_미적용_금액_계산(final int date) {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.BARBEQUE_LIB, 1);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.CHAMPAGNE, 1);
        Discount discount = new Discount(new Menus(order), new EventDay(date));

        //when
        int specialDayDiscount = discount.calculateDiscountBySpecialDay();

        //then
        assertThat(specialDayDiscount).isEqualTo(0);
    }

    @DisplayName("평일엔 디저트 메뉴 1개당 2,023원을 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void 평일_할인_금액_계산(final int date) {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.CHOCO_CAKE, 2);
        order.put(Menu.ICE_CREAM, 4);
        Discount discount = new Discount(new Menus(order), new EventDay(date));

        //when
        int weekdayDiscount = discount.calculateWeekdayDiscount();

        //then
        assertThat(weekdayDiscount).isEqualTo(2023 * 6);
    }

    @DisplayName("주말엔 메인메뉴 1개당 2,023원을 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void 주말_할인_금액_계산(final int date) {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.BARBEQUE_LIB, 2);
        order.put(Menu.T_BONE_STEAK, 1);
        Discount discount = new Discount(new Menus(order), new EventDay(date));

        //when
        int weekendDiscount = discount.calculateWeekendDiscount();

        //then
        assertThat(weekendDiscount).isEqualTo(2023 * 3);
    }

    @DisplayName("증정 가능하면 혜택 금액에 25,000원을 적용한다.")
    @Test
    void 증정가능_혜택_금액_계산() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.BARBEQUE_LIB, 2);
        order.put(Menu.T_BONE_STEAK, 1);
        Discount discount = new Discount(new Menus(order), new EventDay(3));

        //when
        int benefitByPresent = discount.calculateBenefitByPresent();

        //then
        assertThat(benefitByPresent).isEqualTo(25000);
    }

    @DisplayName("증정 불가능하면 혜택 금액은 0원이다.")
    @Test
    void 증정불가능_혜택_금액_계산() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 1);
        Discount discount = new Discount(new Menus(order), new EventDay(3));

        //when
        int benefitByPresent = discount.calculateBenefitByPresent();

        //then
        assertThat(benefitByPresent).isEqualTo(0);
    }

    @DisplayName("26일부턴 디데이할인 금액이 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void 디데이할인_미적용_금액_계산(final int date) {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 1);
        EventDay eventDay = new EventDay(date);
        Discount discount = new Discount(new Menus(order), eventDay);

        //when
        int dDayDiscount = discount.calculateDiscountByDday();

        //then
        assertThat(dDayDiscount).isEqualTo(0);
    }

    @DisplayName("1일 ~ 25일까지는 디데이할인이 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25})
    void 디데이할인_적용_금액_계산(final int date) {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 1);
        EventDay eventDay = new EventDay(date);
        Discount discount = new Discount(new Menus(order), eventDay);
        int expectedDiscount = 1000 + eventDay.calculateDayFromDecember() * 100;

        //when
        int dDayDiscount = discount.calculateDiscountByDday();

        //then
        assertThat(dDayDiscount).isEqualTo(expectedDiscount);
    }

    @DisplayName("총 혜택 금액은 증정 혜택 + 평일,주말할인 금액 + 디데이할인 + 특별할인이다.")
    @Test
    void 총_혜택_금액_계산() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 1);
        Discount discount = new Discount(new Menus(order), new EventDay(1));
        int expectedDiscount = discount.calculateBenefitByPresent() + discount.calculateWeekdayDiscount()
                + discount.calculateWeekendDiscount() + discount.calculateDiscountByDday()
                + discount.calculateDiscountBySpecialDay();

        //when
        int totalBenefit = discount.calculateTotalBenefitPrice();

        //then
        assertThat(totalBenefit).isEqualTo(expectedDiscount);
    }

    @DisplayName("할인 후 예상 지불 금액은 주문 총 금액 - (평일,주말할인 금액 + 디데이할인 + 특별할인)이다.")
    @Test
    void 최종_예상_지불_금액_계산() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 2);
        Menus menus = new Menus(order);
        Discount discount = new Discount(menus, new EventDay(1));
        int finalDiscount = discount.calculateWeekdayDiscount()
                + discount.calculateWeekendDiscount() + discount.calculateDiscountByDday()
                + discount.calculateDiscountBySpecialDay();

        //when
        int finalMoney = discount.calculateFinalPayMoney();

        //then
        assertThat(finalMoney).isEqualTo(menus.calculateTotalMoney() - finalDiscount);
    }

    @DisplayName("총 주문 금액이 10000원 미만이면 할인이 적용되지 않는다.")
    @Test
    void 금액미달_할인_미적용() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.SALAD, 1);
        Menus menus = new Menus(order);
        Discount discount = new Discount(menus, new EventDay(1));

        //when
        int totalBenefitPrice = discount.calculateTotalBenefitPrice();

        //then
        assertThat(totalBenefitPrice).isEqualTo(0);
    }
}
