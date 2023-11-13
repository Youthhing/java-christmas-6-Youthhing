package christmas.utils;

import static christmas.constant.ErrorMessage.INPUT_BLANK_ERROR;
import static christmas.constant.ErrorMessage.MENU_INPUT_ERROR;

import christmas.model.Menu;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    private static final String MENU_DELIMITER = ",";
    private static final String MENU_COUNT_DELIMITER = "-";
    private static final String BLANK = " ";

    public static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException(exception.getMessage());
        }
    }

    public static List<String> splitMenuFromString(String input) {
        try {
            validateHasBlank(input);
            return List.of(input.split(MENU_DELIMITER));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    private static void validateHasBlank(String input) {
        if (input.contains(BLANK)) {
            throw new IllegalArgumentException(INPUT_BLANK_ERROR.getMessage());
        }
    }

    public static Map<Menu, Integer> parseMenuAndCount(List<String> input) {
        try {
            return input.stream()
                    .map(string -> string.split(MENU_COUNT_DELIMITER))
                    .collect(Collectors.toMap(
                            array -> Menu.getMenuByName(array[0]), array -> convertToInt(array[1])
                    ));
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException(MENU_INPUT_ERROR.getMessage());
        }
    }
}
