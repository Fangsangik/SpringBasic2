package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    void singleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBeanTest.class);
        SingletonBeanTest bean = ac.getBean(SingletonBeanTest.class);
        System.out.println("bean = " + bean);
        SingletonBeanTest bean1 = ac.getBean(SingletonBeanTest.class);
        System.out.println("bean1 = " + bean1);
        Assertions.assertThat(bean).isEqualTo(bean1);

        ac.close();

    }

    @Scope("singleton")
   static class SingletonBeanTest {
        @PostConstruct
        public void init(){
            System.out.println("SingleTon init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("singleton destroy");
        }
   }
}
