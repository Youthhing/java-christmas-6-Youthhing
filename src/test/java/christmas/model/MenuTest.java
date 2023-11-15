package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @DisplayName("메뉴 이름이 주어지면 해당 메뉴 Enum을 반환한다.")
    @Test
    void 메뉴_이름_반환() {
        //given
        final String name = "크리스마스파스타";

        //when
        Menu menuByName = Menu.getMenuByName(name);

        //then
        assertThat(menuByName).isEqualTo(Menu.CHRISTMAS_PASTA);
    }

    @DisplayName("메뉴 이름이 존재하지 않으면 None을 반환한다.")
    @Test
    void 메뉴_없음_반환() {
        //given
        final String name = "김치피자탕수육";

        //when
        Menu menuByName = Menu.getMenuByName(name);

        //then
        assertThat(menuByName).isEqualTo(Menu.NO_MENU);
    }

    @DisplayName("디저트 타입의 메뉴들을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크", "아이스크림"})
    void 디저트_타입_목록(String name) {
        //given
        MenuType dessert = MenuType.DESSERT;
        Menu menu = Menu.getMenuByName(name);

        //when
        List<Menu> menuByType = Menu.getMenusByType(dessert);

        //then
        assertThat(menuByType).contains(menu);
    }

    @DisplayName("애피타이저 타입의 메뉴들을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드"})
    void 애피타이저_타입_목록(String name) {
        //given
        MenuType appetizer = MenuType.APPETIZER;
        Menu menu = Menu.getMenuByName(name);

        //when
        List<Menu> menuByType = Menu.getMenusByType(appetizer);

        //then
        assertThat(menuByType).contains(menu);
    }

    @DisplayName("메인 타입의 메뉴들을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
    void 메인_타입_목록(String name) {
        //given
        MenuType main = MenuType.MAIN;
        Menu menu = Menu.getMenuByName(name);

        //when
        List<Menu> menuByType = Menu.getMenusByType(main);

        //then
        assertThat(menuByType).contains(menu);
    }

    @DisplayName("음료 타입의 메뉴들을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    void 음료_타입_목록(String name) {
        //given
        MenuType drink = MenuType.DRINK;
        Menu menu = Menu.getMenuByName(name);

        //when
        List<Menu> menuByType = Menu.getMenusByType(drink);

        //then
        assertThat(menuByType).contains(menu);
    }
}
