package hello.core.order;

import hello.core.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static hello.core.member.MemberGrade.*;
import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPriceTest {

    @BeforeEach
    void beforeEach() {

    }

    RateDiscountPrice rateDiscountPrice = new RateDiscountPrice();

    @Test
    @DisplayName("VIP")
    void rateDiscountTest() {
        Member member = new Member(1L, "vip", VIP);

        int discount = rateDiscountPrice.discount(member, 10000);
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIPX")
    void rateDiscountTest2() {
        Member member = new Member(2L, "basic", BASIC);

        int discount = rateDiscountPrice.discount(member, 10000);
        assertThat(discount).isEqualTo(0);
    }
}