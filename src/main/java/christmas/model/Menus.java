package christmas.model;

import static christmas.constant.ErrorMessage.MENU_COUNT_ERROR;
import static christmas.constant.ErrorMessage.MENU_NAME_ERROR;
import static christmas.constant.ErrorMessage.MENU_SIZE_ERROR;

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
                .filter(menu -> menu.getType() == MenuType.DESSERT)
                .mapToInt(menus::get)
                .sum();
    }

    public int getMainMenuCount() {
        return menus.keySet()
                .stream()
                .filter(menu -> menu.getType() == MenuType.MAIN)
                .mapToInt(menus::get)
                .sum();
    }

    private int calculateEachMoney(Menu menu) {
        return menus.get(menu) * menu.getPrice();
    }

    private void validateNoSuchMenu(Map<Menu, Integer> menus) {
        if (menus.containsKey(Menu.NO_MENU)) {
            throw new IllegalArgumentException(MENU_NAME_ERROR.getMessage());
        }
    }

    private void validateMenuCount(Map<Menu, Integer> menus) {
        List<Integer> counts = new ArrayList<>(menus.values());
        if (hasBelowZero(counts)) {
            throw new IllegalArgumentException(MENU_COUNT_ERROR.getMessage());
        }

    }

    private boolean hasBelowZero(List<Integer> counts) {
        return counts.stream()
                .anyMatch(count -> count <= 0);
    }

    private void validateSize(Map<Menu, Integer> menus) {
        if (getMenuCount(menus) > 20) {
            throw new IllegalArgumentException(MENU_SIZE_ERROR.getMessage());
        }
    }

    private int getMenuCount(Map<Menu, Integer> menus) {
        return menus.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void validateMenuType(Map<Menu, Integer> menus) {
        if (isAllDrink(menus)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ONLY_DRINK_ERROR.getMessage());
        }
    }

    private boolean isAllDrink(Map<Menu, Integer> menus) {
        return menus.keySet()
                .stream()
                .allMatch(menu -> menu.getType() == MenuType.DRINK);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        menus.forEach((menu, integer) ->
                result.append(menu)
                        .append(" ")
                        .append(integer)
                        .append("개\n"));
        return result.toString();
    }
}
