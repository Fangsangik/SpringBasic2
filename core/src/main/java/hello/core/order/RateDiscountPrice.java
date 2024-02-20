package hello.core.order;

import hello.core.member.Member;
import hello.core.member.MemberGrade;

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
