package hello.core.member;

/*
 * 회원 저장소 인터페이스
 * */
public interface MemberRepository {
    void save(Member member); // 회원 저장

    Member findById(Long memberId); // 아이디로 회원 찾기
}
