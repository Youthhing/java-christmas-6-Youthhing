package christmas.utils;

import static christmas.constant.ErrorMessage.INPUT_BLANK_ERROR;
import static christmas.constant.ErrorMessage.MENU_INPUT_ERROR;
import static christmas.constant.StringConstant.BLANK;
import static christmas.constant.StringConstant.MENU_COUNT_DELIMITER;
import static christmas.constant.StringConstant.MENU_DELIMITER;
import static christmas.constant.StringConstant.NUMBER_FORMAT;

import christmas.model.Menu;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

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
                    .collect(Collectors.toMap(array -> Menu.getMenuByName(array[0]), array -> convertToInt(array[1])));
        } catch (final IllegalArgumentException exception) {
            throw new IllegalArgumentException(MENU_INPUT_ERROR.getMessage());
        } catch (final ArrayIndexOutOfBoundsException exception) {
            throw new ArrayIndexOutOfBoundsException(MENU_INPUT_ERROR.getMessage());
        } catch (final IllegalStateException exception) {
            throw new IllegalStateException(MENU_INPUT_ERROR.getMessage());
        }
    }

    public static String integerFormat(int money) {
        DecimalFormat decimalFormat = new DecimalFormat(NUMBER_FORMAT);
        return decimalFormat.format(money);
    }
}
