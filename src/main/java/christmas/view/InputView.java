package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import christmas.utils.Util;
import java.util.List;
import java.util.Map;

public class InputView {

    public int readDate() {
        return Util.convertToInt(input());
    }

    public Map<Menu, Integer> readMenu() {
        List<String> splitMenu = Util.splitMenuFromString(input());
        return Util.parseMenuAndCount(splitMenu);
    }

    private String input() {
        return Console.readLine();
    }
}
