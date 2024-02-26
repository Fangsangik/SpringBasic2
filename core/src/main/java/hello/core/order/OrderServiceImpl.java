package hello.core.order;

import hello.core.member.Member;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository repository;
    @Autowired
    private final DiscountPolicy rateDiscountPolicy;

    @Override
    public Order createOrder(Long id, String productName, int productPrice) {
        Member member = repository.findById(id);
        int discountPrice = rateDiscountPolicy.discount(member, productPrice);

        return new Order(id, productName, productPrice, discountPrice);
    }
}
