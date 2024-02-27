package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberGrade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@MainDiscountPolicy
public class RateDiscountPrice implements DiscountPolicy {
    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == MemberGrade.VIP){
            return price * (discountPercent / 100);
        }
        return 0;
    }
}
