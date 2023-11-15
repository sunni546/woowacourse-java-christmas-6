package christmas.domain;

import static christmas.config.BadgeType.SANTA;
import static christmas.config.EventType.CHRISTMAS_DISCOUNT;
import static christmas.config.EventType.GIFT_EVENT;
import static christmas.config.EventType.SPECIAL_DISCOUNT;
import static christmas.config.EventType.WEEKDAY_DISCOUNT;
import static christmas.config.MenuType.CHAMPAGNE;
import static christmas.config.SeparatorType.LINE;
import static christmas.domain.Order.ORDER_OUTPUT_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionTest {
    private static final int TEST_DATE = 3;
    private static final String TEST_ORDERED_MENU = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
    private static final String DEFAULT_GIFT_MENU = String.format(ORDER_OUTPUT_FORMAT, CHAMPAGNE.getName(), 1);
    private static final int TEST_TOTAL_BENEFIT_AMOUNT = 31246;
    private static final int TEST_DISCOUNTED_PAYMENT_TOTAL = 135754;

    private final Promotion promotion = new Promotion(new Order(TEST_DATE, TEST_ORDERED_MENU));

    @DisplayName("증정 메뉴 얻기")
    @Test
    void getGiftMenu() {
        assertThat(promotion.getGiftMenu()).isEqualTo(DEFAULT_GIFT_MENU);
    }

    @DisplayName("혜택 내역 얻기")
    @Test
    void getDetails() {
        StringBuilder details = promotion.getDetails();
        
        assertThat(details.toString()).contains(
                CHRISTMAS_DISCOUNT.getTitle(),
                WEEKDAY_DISCOUNT.getTitle(),
                SPECIAL_DISCOUNT.getTitle(),
                GIFT_EVENT.getTitle()
        );
    }

    @DisplayName("총혜택 금액 얻기")
    @Test
    void getTotalBenefitAmount() {
        assertThat(promotion.getTotalBenefitAmount()).isEqualTo(TEST_TOTAL_BENEFIT_AMOUNT);
    }

    @DisplayName("할인 후 예상 결제 금액 얻기")
    @Test
    void getDiscountedPaymentTotal() {
        assertThat(promotion.getDiscountedPaymentTotal()).isEqualTo(TEST_DISCOUNTED_PAYMENT_TOTAL);
    }

    @DisplayName("12월 이벤트 배지 얻기")
    @Test
    void getDecemberEventBadge() {
        assertThat(promotion.getDecemberEventBadge()).isEqualTo(SANTA.getName() + LINE.getSymbol());
    }
}