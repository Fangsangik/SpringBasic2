package hello.core.order;

import hello.core.member.Member;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository repository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository repository, DiscountPolicy discountPolicy) {
        this.repository = repository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long id, String productName, int productPrice) {
        Member member = repository.findById(id);
        int discountPrice = discountPolicy.discount(member, productPrice);

        return new Order(id, productName, productPrice, discountPrice);
    }
}
