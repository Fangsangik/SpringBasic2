package hello.core.autowired;

import hello.core.config.AutoAppConfig;
import hello.core.member.Member;
import hello.core.member.MemberGrade;
import hello.core.order.DiscountPolicy;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    @Test
    void findAllBean() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", MemberGrade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(10000);
        //when
        //then
    }

    @RequiredArgsConstructor
    static class DiscountService {
        //Map의 경우 키의 값에 해당 하는 값들을 넣어주고
        private final Map<String, DiscountPolicy> policyMap; //DiscountService -> Map으로 모든 DiscountPolicy 주입 (rate, fix)
        //List의 경우 모든 값을 넣어준다.
        private final List<DiscountPolicy> policies;

        public int discount(Member member, int price, String discountCode) { //fix 넘어오면 map에서 fix 스프링 빈을 찾아서 실행 rate -> rate 찾아서 실행
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}
