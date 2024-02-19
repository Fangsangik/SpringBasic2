package hello.core.order;

import hello.core.member.Member;
import hello.core.member.MemberGrade;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void serviceTest(){
        long memberId = 1L;
        Member member = new Member(memberId, "황상익", MemberGrade.BASIC);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "축구공", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(10000);
    }

}