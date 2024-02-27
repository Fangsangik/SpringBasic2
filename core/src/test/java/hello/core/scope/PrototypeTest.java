package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void protoType(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        System.out.println("findBean1");
        ProtoTypeBean bean = ac.getBean(ProtoTypeBean.class);
        System.out.println("findBean2");
        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("bean = " + bean);
        System.out.println("bean1 = " + bean1);
        Assertions.assertThat(bean).isNotSameAs(bean1);
        ac.close();
    }

    @Test
    void protoTypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean bean = ac.getBean(ProtoTypeBean.class);
        bean.addCnt();
        Assertions.assertThat(bean.getCnt()).isEqualTo(1);
    }

    @Scope("prototype")
    static class ProtoTypeBean {

        private int cnt = 0;

        @PostConstruct
        public void init(){
            System.out.println("생성");
        }

        @PreDestroy
        public void destory(){
            System.out.println("파괴");
        }

        public void addCnt(){
            cnt ++;
        }

        public int getCnt(){
            return cnt;
        }
    }
}
