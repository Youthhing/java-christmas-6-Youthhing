package christmas.controller;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.EventDay;
import christmas.model.Menus;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void init() {
        outputView.printWelcomeMessage();
        EventDay eventDay = tryCreateEventDay();
        Menus menus = tryCreateMenus();

        outputView.printPreviewBenefitMessage(eventDay);
        outputView.printOrderedMenus(menus);
        outputView.printBeforeTotalMoney(menus.calculateTotalMoney());

        Discount discount = new Discount(menus, eventDay);
        outputView.printPresentMenu(menus);
        outputView.printEmptyLine();
        outputView.printBenefitList(discount);
        outputView.printTotalDiscount(discount.calculateTotalBenefitPrice());
        outputView.printEmptyLine();
        outputView.printFinalPayMoney(discount.calculateFinalPayMoney());
        outputView.printBadge(Badge.getBadgeByDiscount(discount.calculateTotalBenefitPrice()));
    }

    private EventDay tryCreateEventDay() {
        outputView.printInputDateMessage();
        try {
            return new EventDay(inputView.readDate());
        } catch (IllegalArgumentException exception) {
            outputView.printDateInputError();
            return tryCreateEventDay();
        }
    }

    private Menus tryCreateMenus() {
        outputView.printInputMenuMessage();
        try {
            return new Menus(inputView.readMenu());
        } catch (IllegalArgumentException | IndexOutOfBoundsException | IllegalStateException exception) {
            outputView.printMenuInputError();
            return tryCreateMenus();
        }
    }
}
