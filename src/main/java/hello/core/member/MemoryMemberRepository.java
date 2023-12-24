package hello.core.member;
/*
 * 메모리 회원 저장소 구현체
 * */

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    // 동시성 이슈가 발생할 수 있으므로 ConcurrentHashMap을 사용한다.
    private static final Map<Long, Member> store = new HashMap<>(); // 저장소리 Map 으로

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
