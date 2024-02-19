package hello.core.order;

import hello.core.member.Member;

public class RateDiscountPrice implements Discount{
    @Override
    public int discount(Member member, int price) {
        return 0;
    }
}
