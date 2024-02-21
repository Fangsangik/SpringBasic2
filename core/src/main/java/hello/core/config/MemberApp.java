package hello.core.config;

import hello.core.member.Member;
import hello.core.member.MemberGrade;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService service = appConfig.memberService();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService service = ac.getBean("MemberService", MemberService.class);

        Member member = new Member(1L, "황상익", MemberGrade.BASIC);
        service.join(member);
        System.out.println("member = " + member);

        Member findMember = new Member(1L, "황상익", MemberGrade.BASIC);
        service.findMember(1L);
        System.out.println("findMember = " + findMember);
    }
}
