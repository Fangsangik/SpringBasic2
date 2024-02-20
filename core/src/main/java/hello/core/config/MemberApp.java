package hello.core.config;

import hello.core.member.Member;
import hello.core.member.MemberGrade;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService service = appConfig.memberService();
        Member member = new Member(1L, "황상익", MemberGrade.BASIC);
        service.join(member);
        System.out.println("member = " + member);

        Member findMember = new Member(1L, "황상익", MemberGrade.BASIC);
        service.findMember(1L);
        System.out.println("findMember = " + findMember);
    }
}
