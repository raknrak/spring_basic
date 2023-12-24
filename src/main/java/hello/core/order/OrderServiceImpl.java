package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * 주문 서비스 구현체
 * */
@Component
public class OrderServiceImpl implements OrderService {
    //회원과 주문 정책이 필요함
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 정액 할인 -> 정률 할인으로 정책 변경
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 인터페이스에만 의존하도록 의존관계를 변경
    //private DiscountPolicy discountPolicy; // 이 상태에서 바로 테스트시 NullpointException 발생
    // 해결하기위해 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야함.
    private final MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않는다.
    // 단지 DiscountPolicy 인터페이스만 의존한다.
    // OrderServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지) 알 수 없다.
    // OrderServiceImpl 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정한다.
    //OrderServiceImpl는 MemoryMemberRepository, FixDiscountPolicy 객체의 의존관계가 주입된다

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // test용 코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
