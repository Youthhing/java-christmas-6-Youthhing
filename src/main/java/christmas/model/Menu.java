package christmas.model;

import static christmas.model.MenuType.APPETIZER;
import static christmas.model.MenuType.DESSERT;
import static christmas.model.MenuType.DRINK;
import static christmas.model.MenuType.MAIN;
import static christmas.model.MenuType.NON_TYPE;

import java.util.Arrays;
import java.util.List;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    SALAD("시저샐러드", 8000, APPETIZER),
    T_BONE_STEAK("티폰스테이크", 55000, MAIN),
    BARBEQUE_LIB("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHOCO_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),
    ZERO_COKE("제로콜라", 3000, DRINK),
    RED_WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK),
    NO_MENU("없음", 0, NON_TYPE);


    private final String name;
    private final int price;
    private final MenuType type;

    Menu(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static Menu getMenuByName(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElse(NO_MENU);
    }

    public List<Menu> getMenuByType(MenuType type) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.type == type)
                .toList();
    }
}
