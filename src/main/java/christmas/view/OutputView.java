package christmas.view;

import static christmas.constant.ErrorMessage.DATE_INPUT_ERROR;
import static christmas.constant.ErrorMessage.MENU_INPUT_ERROR;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String INPUT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String PREVIEW_BENEFIT_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printInputDateMessage() {
        System.out.println(INPUT_DATE_MESSAGE);
    }

    public void printInputMenuMessage() {
        System.out.println(INPUT_MENU_MESSAGE);
    }

    public void printDateInputError() {
        System.out.println(DATE_INPUT_ERROR);
    }

    public void printMenuInputError() {
        System.out.println(MENU_INPUT_ERROR);
    }

    public void printPreviewBenefitMessage() {
        System.out.println(PREVIEW_BENEFIT_MESSAGE);
    }
}
