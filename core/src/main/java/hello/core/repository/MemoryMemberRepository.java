package hello.core.repository;

import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MemoryMemberRepository implements MemberRepository{
    private final MemberRepository memberRepository;

    public MemoryMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private static HashMap<Long, Member> store = new HashMap<>();



    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }
}
