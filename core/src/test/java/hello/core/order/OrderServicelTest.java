package hello.core.order;

import hello.core.config.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberGrade;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig config = new AppConfig();
        memberService = config.memberService();
        orderService = config.orderService();
    }


    @Test
    void serviceTest(){
        long memberId = 1L;
        Member member = new Member(memberId, "황상익", MemberGrade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "축구공", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}