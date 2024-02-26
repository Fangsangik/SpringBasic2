package hello.core.config;

import hello.core.order.DiscountPolicy;
import hello.core.order.FixDiscountPrice;
import hello.core.order.RateDiscountPrice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountPolicyConfig {

    @Bean
    public DiscountPolicy rateDiscountPolicy(){
        return new RateDiscountPrice();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy(){
        return new FixDiscountPrice();
    }
}
