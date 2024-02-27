package hello.core.config;

import hello.core.lifecycle.NetworkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeCycleConfig {
    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient(){
        NetworkClient client = new NetworkClient();
        client.setUrl("http://");
        return client;
    }
}
