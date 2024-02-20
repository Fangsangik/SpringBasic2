package hello.core.service;

import hello.core.config.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberGrade;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {
    MemberService service;

    @BeforeEach
    public void beforeEach() {
        AppConfig config = new AppConfig();
        service = config.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "황상익", MemberGrade.BASIC);
        service.join(member);

        Member findMember = service.findMember(1L);
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}