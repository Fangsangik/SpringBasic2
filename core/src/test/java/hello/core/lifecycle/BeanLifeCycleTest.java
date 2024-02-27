package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {
        ConfigurableApplicationContext ca = new AnnotationConfigApplicationContext(lifeCycleConfig.class);
        NetworkClient client = ca.getBean(NetworkClient.class);
        ca.close();
    }

    @Configuration
    static class lifeCycleConfig {

        @Bean
       public NetworkClient networkClient(){
           NetworkClient client = new NetworkClient();
           client.setUrl("http://hello-spring.dev");
           return networkClient();
       }
    }
}
