package hello.core.order;

import hello.core.member.Member;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository repository = new MemoryMemberRepository();
    private final Discount discount = new RateDiscountPrice();

    @Override
    public Order createOrder(Long id, String productName, int productPrice) {
        Member member = repository.findById(id);
        int discountPrice = discount.discount(member, productPrice);

        return new Order(id, productName, productPrice, discountPrice);
    }
}
