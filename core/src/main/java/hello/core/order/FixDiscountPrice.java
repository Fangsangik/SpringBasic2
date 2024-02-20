package hello.core.order;

import hello.core.member.Member;
import hello.core.member.MemberGrade;

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
