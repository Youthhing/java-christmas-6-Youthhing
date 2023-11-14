package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.constant.ErrorMessage;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenusTest {

    @DisplayName("메뉴에 존재하지 않는 메뉴면 예외를 발생시킨다.")
    @Test
    void 메뉴_이름_존재_예외_처리() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.NO_MENU, 2);

        //when,then
        Assertions.assertThatThrownBy(() -> new Menus(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_NAME_ERROR.getMessage());
    }

    @DisplayName("주문한 메뉴의 개수가 0이면 예외를 발생시킨다.")
    @Test
    void 메뉴_0개_예외_처리() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.SALAD, 0);

        //when,then
        Assertions.assertThatThrownBy(() -> new Menus(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_ERROR.getMessage());
    }

    @DisplayName("주문한 메뉴의 개수가 음수이면 예외를 발생시킨다.")
    @Test
    void 메뉴_음수_예외_처리() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.SALAD, -10);

        //when,then
        Assertions.assertThatThrownBy(() -> new Menus(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_COUNT_ERROR.getMessage());
    }

    @DisplayName("주문한 메뉴의 총 개수가 20초과면 예외를 발생시킨다.")
    @Test
    void 메뉴_개수_초과_예외_처리() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.SALAD, 10);
        order.put(Menu.BARBEQUE_LIB, 5);
        order.put(Menu.CHAMPAGNE, 2);
        order.put(Menu.CHOCO_CAKE, 4);

        //when,then
        Assertions.assertThatThrownBy(() -> new Menus(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_SIZE_ERROR.getMessage());
    }

    @DisplayName("메뉴와 개수가 주어지면 주문한 메뉴들이 생성된다.")
    @Test
    void 주문_목록_생성_기능() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.SALAD, 10);
        order.put(Menu.BARBEQUE_LIB, 5);
        order.put(Menu.CHAMPAGNE, 2);

        //when, then
        Assertions.assertThatNoException().isThrownBy(() -> new Menus(order));
    }

    @DisplayName("음료수만 주문하면 예외가 발생한다.")
    @Test
    void 음료수만_주문시_예외_처리() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.RED_WINE, 10);
        order.put(Menu.CHAMPAGNE, 5);
        order.put(Menu.ZERO_COKE, 1);

        //when,then
        Assertions.assertThatThrownBy(() -> new Menus(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_ONLY_DRINK_ERROR.getMessage());
    }

    @DisplayName("계산한 주문 금액과 총 주문 금액이 일치한다.")
    @Test
    void 주문_총_금액_계산_기능() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.CHOCO_CAKE, 2);
        order.put(Menu.BARBEQUE_LIB, 1);
        order.put(Menu.ICE_CREAM, 4);
        Menus menus = new Menus(order);

        //when
        int totalMoney = menus.calculateTotalMoney();

        //then
        Assertions.assertThat(totalMoney).isEqualTo(104000);
    }

    @DisplayName("총 주문 금액이 12만원 이상이면 증정 가능을 반환한다.")
    @Test
    void 증정_가능() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.BARBEQUE_LIB, 3);
        Menus menus = new Menus(order);

        //when
        boolean toGivePresent = menus.canPresent();

        //then
        assertTrue(toGivePresent);
    }

    @DisplayName("총 주문 금액이 12만원 미만이면 증정이 불가능하다.")
    @Test
    void 증정_불가능() {
        //given
        final Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.BARBEQUE_LIB, 2);
        Menus menus = new Menus(order);

        //when
        boolean toGivePresent = menus.canPresent();

        //then
        assertFalse(toGivePresent);
    }
}
