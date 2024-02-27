package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void singleWithPrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClinetBean.class, PrototypeBean.class);
        ClinetBean bean = ac.getBean(ClinetBean.class);
        int cnt1 = bean.logic();
        assertThat(cnt1).isEqualTo(1);

        ClinetBean bean2 = ac.getBean(ClinetBean.class);
        int cnt2 = bean2.logic();
        assertThat(cnt2).isEqualTo(2);

    }

    static class ClinetBean {
        private final PrototypeBean prototypeBean;

        @Autowired
        public ClinetBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic(){
            prototypeBean.addCnt();
            int cnt = prototypeBean.getCnt();
            return cnt;
        }
    }

    static class PrototypeBean {
        private int cnt= 0;

        public void addCnt(){
            cnt++;
        }

        public int getCnt() {
            return cnt;
        }

        @PostConstruct
        public void init(){
            System.out.println(this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("destroy");
        }
    }
}
