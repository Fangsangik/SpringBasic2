package hello.core.repository;

import hello.core.member.Member;

import java.util.HashMap;

public class MemoryMemberRepository implements MemberRepository{
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
