package christmas.model;

import static christmas.constant.ErrorMessage.MENU_COUNT_ERROR;
import static christmas.constant.ErrorMessage.MENU_NAME_ERROR;
import static christmas.constant.ErrorMessage.MENU_SIZE_ERROR;
import static christmas.constant.StringConstant.BLANK;
import static christmas.constant.StringConstant.COUNT;
import static christmas.constant.StringConstant.NEXT_LINE;

import christmas.constant.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Menus {
    private final Map<Menu, Integer> menus;

    public Menus(Map<Menu, Integer> menus) {
        validateNoSuchMenu(menus);
        validateMenuCount(menus);
        validateSize(menus);
        validateMenuType(menus);
        this.menus = menus;
    }

    public int calculateTotalMoney() {
        Set<Menu> menuNames = menus.keySet();
        return menuNames.stream()
                .mapToInt(this::calculateEachMoney)
                .sum();
    }

    public boolean canPresent() {
        return calculateTotalMoney() >= 120000;
    }

    public int getDessertMenuCount() {
        return menus.keySet()
                .stream()
                .filter(Menus::isContainDessert)
                .mapToInt(menus::get)
                .sum();
    }

    public int getMainMenuCount() {
        return menus.keySet()
                .stream()
                .filter(Menus::isContainMain)
                .mapToInt(menus::get)
                .sum();
    }

    private static boolean isContainDessert(final Menu menu) {
        return Menu.getMenusByType(MenuType.DESSERT).contains(menu);
    }

    private static boolean isContainMain(Menu menu) {
        return Menu.getMenusByType(MenuType.MAIN).contains(menu);
    }

    private int calculateEachMoney(Menu menu) {
        return menus.get(menu) * menu.getPrice();
    }

    private void validateNoSuchMenu(final Map<Menu, Integer> menus) {
        if (menus.containsKey(Menu.NO_MENU)) {
            throw new IllegalArgumentException(MENU_NAME_ERROR.getMessage());
        }
    }

    private void validateMenuCount(final Map<Menu, Integer> menus) {
        List<Integer> counts = new ArrayList<>(menus.values());
        if (hasBelowZero(counts)) {
            throw new IllegalArgumentException(MENU_COUNT_ERROR.getMessage());
        }
    }

    private boolean hasBelowZero(final List<Integer> counts) {
        return counts.stream()
                .anyMatch(count -> count <= 0);
    }

    private void validateSize(final Map<Menu, Integer> menus) {
        if (getMenuCount(menus) > 20) {
            throw new IllegalArgumentException(MENU_SIZE_ERROR.getMessage());
        }
    }

    private int getMenuCount(final Map<Menu, Integer> menus) {
        return menus.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void validateMenuType(final Map<Menu, Integer> menus) {
        if (hasOnlyDrinks(menus)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ONLY_DRINK_ERROR.getMessage());
        }
    }

    private boolean hasOnlyDrinks(final Map<Menu, Integer> menus) {
        return menus.keySet()
                .stream()
                .allMatch(menu -> menu.getType() == MenuType.DRINK);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        menus.forEach((menu, integer) ->
                result.append(menu)
                        .append(BLANK)
                        .append(integer)
                        .append(COUNT)
                        .append(NEXT_LINE));
        return result.toString();
    }
}
