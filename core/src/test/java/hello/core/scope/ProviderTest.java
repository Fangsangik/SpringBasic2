package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProviderTest {
    @Test
    void providerTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
    }

    static class ClientBean {
        @Autowired
        //private ApplicationContext ac;
        private ObjectProvider<PrototypeBean> prototypeBeans;

        public int logic(){
            PrototypeBean bean = prototypeBeans.getObject();
            bean.addCnt();
            int cnt = bean.getCnt();
            return cnt;
        }
    }

    static class PrototypeBean {
        private int cnt = 0;

        public void addCnt(){
            cnt++;
        }

        public int getCnt(){
            return cnt;
        }

        @PostConstruct
        public void init(){
            System.out.println("생성");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("파괴");
        }
    }
}
