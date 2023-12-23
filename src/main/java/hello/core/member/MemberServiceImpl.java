package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // 구현체를 선택해야 함.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; // 변경 후
    // 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository를 의존하지 않고
    // 단지 MemberRepository만 의존한다.
    // MemberServiceImpl입장에서는 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)알 수 없다.
    // MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
    //MemberServiceImpl은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.

    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자 생성
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
