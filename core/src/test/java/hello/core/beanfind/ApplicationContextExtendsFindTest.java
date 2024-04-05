package hello.core.beanfind;

import hello.core.order.DiscountPolicy;
import hello.core.order.FixDiscountPrice;
import hello.core.order.RateDiscountPrice;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPrice();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPrice();
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입 조회시, 자식 둘이상 있으면, 빈 이름을 지정 하면 된다")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPrice.class);
    }

    @Test
    @DisplayName("특정하위타입 조회")
    void findBeanByType(){
        RateDiscountPrice rateDiscountPrice = ac.getBean(RateDiscountPrice.class);
        assertThat(rateDiscountPrice).isInstanceOf(RateDiscountPrice.class);
    }

    @Test
    @DisplayName("부모티입으로 모두 조회")
    void findBeanByParentAllType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.keySet());
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회 - Object")
    void findAllBeansByObjectType(){
        Map<String, Objects> beansOfType = ac.getBeansOfType(Objects.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.keySet());
        }
    }
}
