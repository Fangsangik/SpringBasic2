package hello.core.service;

import hello.core.member.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void join(Member member);

    Member findMember(long id);
}
