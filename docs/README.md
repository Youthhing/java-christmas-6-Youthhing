# 📝 개발 요구사항 정리

---

### 개발 목적

12월 우테코 식당 이벤트에 따른 할인 혜택을 보여주는 시스템을 개발한다.

### 방문 날짜

- 고객에게 식당 방문 날짜를 입력 받는다.
- 방문일은 12월 1일부터 31일까지이다.
- 12월의 일요일과 크리스마스 당일은 별표시가 된 날이다.

### 주문 메뉴

- 고객에게 주문할 메뉴와 개수를 입력 받는다.
- 주문 후 고객이 주문한 메뉴와 개수를 보여준다.
- 주문할 메뉴는 애피타이저, 메인, 디저트, 음료의 타입을 갖는다.
- 각각의 메뉴는 이름과 금액, 타입을 갖는다.
- 할인 전 총 주문 금액이 12만원 이상이면, 샴페인 1개를 증정한다.
- 증정 메뉴가 존재하면 해당 메뉴를 출력하고 존재하지 않으면 “없음”을 출력한다.

### 할인 혜택

- 고객에게 받을 혜택을 계산해 보여준다.
- 혜택 내역은 고객에게 적용된 혜택 내역만을 보여준다.
- 총 혜택 금액이란, 할인 금액의 합계 + 증정 메뉴의 가격으로 정의한다.
- 할인 후 예상 결제 금액이란, 할인 전 총 주문 금액 - 할인 금액으로 정의한다.
- 날짜가 평일, 주말인지 여부에 따라 할인 금액이 구분된다.

  평일: 디저트 메뉴 1개당 2,023원 할인

  주말: 메인 메뉴 1개당 2,023원 할인

- 날짜가 별표시된 날짜이면 특별할인 1,000원을 적용한다.

  별표시된 날짜: 12월의 일요일과 크리스마스 당일

- 크리스마스까지의 디데이를 계산해 크리스마스 당일까지 디데이 할인을 적용한다.

  기본 1,000원 + 100원 * (12월 1일부터 지난 날짜)

- 샴페인을 증정하면 할인 금액에 25,000원을 적용한다.

### 이벤트 배지

- 할인 금액에 따라 이벤트 배지를 부여한다.

    - 5천원 미만: 없음
    - 5천원 이상: 별
    - 1만원 이상: 트리
    - 2만원 이상: 산타

### 예외 처리

- 모든 에러 메시지는 “[ERROR]”로 시작한다.
- 개발 과정에서 예외 상황이 발생하면 에러 메시지를 출력한다.
- 날짜 입력, 주문 번호 입력 시 예외가 발생하면 메시지를 출력 후 해당 입력을 다시 받는다.

# 🚀구현할 기능 목록

---

### 날짜 입력 기능

- [ ]  12월에 예약할 날짜를 입력 받는다.

예외처리

- 입력이 숫자 형식이 아니면 예외를 발생시킨다.

### 메뉴 입력 기능

- [x]  주문할 메뉴 목록을 입력 받는다.
    - `-` 을 기준으로 메뉴와 개수를 구분하며 `,` 를 기준으로 메뉴를 구분한다.

예외 처리

- 메뉴가 `,`으로 구분되지 않을때
- 메뉴이름과 개수가 `-`으로 구분되지 않을때
- 메뉴의 개수가 숫자형식이 아닐때

### 출력 기능

- [x]  초기 안내 메시지 출력 기능
- [x]  메뉴 입력 안내 메시지 출력 기능
- [ ]  주문한 메뉴와 개수 출력 기능
- [ ]  할인 전 총 주문 금액을 출력한다.
- [ ]  증정 메뉴를 출력한다.
- [ ]  혜택 내역을 출력한다.
- [ ]  총 혜택 금액을 출력한다.
- [ ]  할인 후 예상 결제 금액을 출력한다.
- [ ]  이벤트 뱃지를 출력한다.

## 날짜

---

### 날짜 생성 기능

- [ ]  특정 값이 주어지면 해당 Event 날짜를 생성한다.

예외 처리

- 입력된 날짜 범위가 1~31이 아니면 예외를 발생시킨다.

### 날짜 구분 기능

- [ ]  평일, 주말 구분 기능
- [ ]  Special Day인지 아닌지 구분하는 기능

### 디데이 계산 기능

- [ ]  크리스마스까지의 디데이를 계산해서 반환하는 기능

## 메뉴

---

### 주문한 메뉴 목록 생성 기능

- [x]  회원이 주문한 메뉴 리스트를 생성한다.

예외 처리

- [x]  메뉴 이름이 존재하지 않으면 예외를 발생시킨다.
- [x]  메뉴 개수가 0미만이면 예외를 발생시킨다.
- [x]  메뉴 개수가 20개를 넘어가면 예외를 발생시킨다.

### 메뉴 개수 계산 기능

- [ ]  주문한 메뉴 목록을 보고 총 개수를 계산한다

### 주문 메뉴 총 금액 계산 기능

- [ ]  주문한 메뉴 목록의 총 금액을 계산한다.

### 증정 여부 판단 기능

- [ ]  총 주문 금액이 12만원을 넘어가면 증정한다.

## 할인

---

### 최종 할인 금액 계산 기능

- [ ]  디데이, 평일 또는 주말, 특별할인 여부, 증정 여부에 따른 최종 할인 금액을 계산한다.

### 증정 여부에 따른 할인 금액 계산 기능

- [ ]  증정 여부에 따라 할인 금액을 계산한다. 증정하면 할인 금액에 25,000원을 포함한다.

### 평일, 주말여부에 따른 할인 금액 계산 기능

- [ ]  평일이면 디저트 메뉴 1개당 2023원을 할인한다.
- [ ]  주말이면 메인 메뉴 1개당 2023원을 할인한다.

### 특별 날짜 여부에 따른 할인 금액 계산 기능

- [ ]  이벤트 날짜가 특별할인날이면 1000원을 할인한다.

### 뱃지 판별 기능

- [ ]  할인 금액에 따라 뱃지를 판별한다.

    - 5천원 미만: 없음
    - 5천원 이상: 별
    - 1만원 이상: 트리
    - 2만원 이상: 산타