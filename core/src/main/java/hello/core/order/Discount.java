package hello.core.order;

import hello.core.member.Member;

public interface Discount {
    int discount(Member member, int price);
}
