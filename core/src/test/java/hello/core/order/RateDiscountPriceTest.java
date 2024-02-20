package hello.core.order;

import hello.core.member.Member;
import hello.core.member.MemberGrade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPriceTest {

    RateDiscountPrice rateDiscountPrice = new RateDiscountPrice();

    @Test
    @DisplayName("VIP")
    void rateDiscountTest(){
        Member member = new Member(1L, "vip", MemberGrade.VIP);

        int discount = rateDiscountPrice.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIPX")
    void rateDiscountTest2(){
        Member member = new Member(2L, "basic", MemberGrade.BASIC);

        int discount = rateDiscountPrice.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }

}