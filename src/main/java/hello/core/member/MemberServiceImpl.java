package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // 구현체를 선택해야 함.
    private static final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
