package hello.core.order;

import hello.core.member.Member;
import hello.core.member.MemberGrade;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("fixDiscountPolicy")
public class FixDiscountPrice implements DiscountPolicy {

    private int discountAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == MemberGrade.VIP){
            return discountAmount;
        } else {
            return 0;
        }
    }
}
