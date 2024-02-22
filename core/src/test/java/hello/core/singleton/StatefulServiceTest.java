package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        //userA는 10000원을 기대했지만 20000원 찍힘
        //service1 이던 service2던 관계 x 같은 instance 사용
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

//        assertThat(statefulService1.getPrice()).isEqualTo(10000);
//        assertThat(statefulService2.getPrice()).isEqualTo(20000);

    }

    @Test
    void statefulService(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);

        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

//        assertThat(statefulService1.getPrice()).isEqualTo(10000);
//        assertThat(statefulService2.getPrice()).isEqualTo(20000);

    }

    @Configuration
    static class TestConfig {

       @Bean
        public StatefulService statefulService(){
           return new StatefulService();
       }
    }
}
