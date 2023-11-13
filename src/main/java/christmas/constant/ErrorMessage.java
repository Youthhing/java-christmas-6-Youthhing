package christmas.constant;

public enum ErrorMessage {
    MENU_SIZE_ERROR("[ERROR] 메뉴는 최대 20개까지만 주문할 수 있습니다."),
    MENU_INPUT_ERROR("[ERROR] 메뉴 입력 형식을 지켜주세요."),
    MENU_COUNT_ERROR("[ERROR] 메뉴의 개수는 1개 이상이어야 합니다."),
    MENU_NAME_ERROR("[ERROR] 메뉴의 이름을 정확히 입력해주세요."),
    INPUT_BLANK_ERROR("[ERROR] 입력에 공백이 들어갈 수 없습니다."),
    NOT_ONLY_DRINK_ERROR("[ERROR] 음료수만 주문할 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
