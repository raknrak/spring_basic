package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    public void AutowiredOption(){
        ApplicationContext ac =  new AnnotationConfigApplicationContext(TestBean.class);
        //ac.getBean(TestBean.class);

    }
    static class TestBean{
        // 호출 안됨
        @Autowired(required = false)
        public void setNotBean1(Member member1) {
            System.out.println("setBean1 = " + member1);
        }

        //null 호출
        @Autowired
        public void setBean2(@Nullable Member member2) {
            System.out.println("setBean2 = " + member2);
        }

        //Optional.empty 호출
        @Autowired(required = false)
        public void setNotBean3(Optional<Member> member3) {
            System.out.println("setBean3 = " + member3);
        }

    }
}
