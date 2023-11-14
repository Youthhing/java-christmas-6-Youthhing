package christmas.view;

import static christmas.constant.ErrorMessage.DATE_INPUT_ERROR;
import static christmas.constant.ErrorMessage.MENU_INPUT_ERROR;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.EventDay;
import christmas.model.Menu;
import christmas.model.Menus;
import christmas.utils.Util;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String INPUT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String PREVIEW_BENEFIT_MESSAGE_FRONT = "12월 ";
    private static final String PREVIEW_BENEFIT_MESSAGE_BACK = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDERED_MENU = "<주문 메뉴>";
    private static final String BEFORE_DISCOUNT_TOTAL_MONEY = "<할인 전 총주문 금액>";
    private static final String WON = "원";
    private static final String PRESENT_MENU = "<증정 메뉴>";
    private static final String COUNT = " 1개";
    private static final String BENEFIT_LIST = "<혜택 내역>";
    private static final String XMAS_DDAY_DISCOUNT = "크리스마스 디데이 할인: ";
    private static final String WEEKDAY_DISCOUNT = "평일 할인: ";
    private static final String WEEKEND_DISCOUNT = "주말 할인: ";
    private static final String SPECIAL_DISCOUNT = "특별 할인: ";
    private static final String PRESENT_EVENT = "증정 이벤트: ";
    private static final String MINUS = "-";
    private static final String FINAL_PAY_MONEY = "<할인 후 예상 결제 금액>";
    private static final String FINAL_DISCOUNT_MONEY = "<총 혜택 금액>";
    private static final String DECEMBER_EVENT_BADGE = "<12월 이벤트 배지>";


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

    public void printPreviewBenefitMessage(EventDay date) {
        System.out.println(PREVIEW_BENEFIT_MESSAGE_FRONT + date + PREVIEW_BENEFIT_MESSAGE_BACK);
        printEmptyLine();
    }

    public void printOrderedMenus(Menus menus) {
        System.out.println(ORDERED_MENU);
        System.out.println(menus);
    }

    public void printBeforeTotalMoney(int totalMoney) {
        System.out.println(BEFORE_DISCOUNT_TOTAL_MONEY);
        System.out.println(Util.integerFormat(totalMoney) + WON);
        printEmptyLine();
    }

    public void printPresentMenu(Menus menus) {
        System.out.println(PRESENT_MENU);
        if (menus.canPresent()) {
            System.out.println(Menu.CHAMPAGNE + COUNT);
            return;
        }
        System.out.println(Menu.NO_MENU);
    }

    public void printBenefitList(Discount discount) {
        System.out.println(BENEFIT_LIST);
        printXmasDiscount(discount);
        printWeekdayDiscount(discount);
        printWeekendDiscount(discount);
        printSpecialDayDiscount(discount);
        printPresentDiscount(discount);
        printEmptyLine();
    }

    public void printTotalDiscount(int discount) {
        System.out.println(FINAL_DISCOUNT_MONEY);
        if (discount == 0) {
            System.out.println(discount + WON);
            return;
        }
        System.out.println(MINUS + Util.integerFormat(discount) + WON);
    }

    public void printFinalPayMoney(int finalMoney) {
        System.out.println(FINAL_PAY_MONEY);
        System.out.println(Util.integerFormat(finalMoney) + WON);
        printEmptyLine();
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printBadge(Badge badgeByDiscount) {
        System.out.println(DECEMBER_EVENT_BADGE);
        System.out.println(badgeByDiscount);
    }

    private void printXmasDiscount(Discount discount) {
        if (discount.calculateDiscountByDday() > 0) {
            System.out.println(
                    XMAS_DDAY_DISCOUNT + MINUS + Util.integerFormat(discount.calculateDiscountByDday()) + WON);
        }
    }

    private void printWeekendDiscount(Discount discount) {
        if (discount.calculateWeekendDiscount() > 0) {
            System.out.println(
                    WEEKEND_DISCOUNT + MINUS + Util.integerFormat(discount.calculateWeekendDiscount()) + WON);
        }
    }

    private void printWeekdayDiscount(Discount discount) {
        if (discount.calculateWeekdayDiscount() > 0) {
            System.out.println(
                    WEEKDAY_DISCOUNT + MINUS + Util.integerFormat(discount.calculateWeekdayDiscount()) + WON);
        }
    }

    private void printSpecialDayDiscount(Discount discount) {
        if (discount.calculateDiscountBySpecialDay() > 0) {
            System.out.println(
                    SPECIAL_DISCOUNT + MINUS + Util.integerFormat(discount.calculateDiscountBySpecialDay()) + WON);
        }
    }

    private void printPresentDiscount(Discount discount) {
        if (discount.calculateBenefitByPresent() > 0) {
            System.out.println(PRESENT_EVENT + MINUS + Util.integerFormat(discount.calculateBenefitByPresent()) + WON);
        }
    }
}
