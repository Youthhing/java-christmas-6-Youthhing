package christmas.model;

import christmas.constant.ErrorMessage;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
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
}
