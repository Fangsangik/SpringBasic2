package hello.core.config;

import hello.core.order.*;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }


    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }


    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPrice();
        return new RateDiscountPrice();
    }
}
