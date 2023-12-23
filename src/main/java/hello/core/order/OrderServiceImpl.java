package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

/*
 * 주문 서비스 구현체
 * */
public class OrderServiceImpl implements OrderService {
    //회원과 주문 정책이 필요함
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 정액 할인 -> 정률 할인으로 정책 변경
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 인터페이스에만 의존하도록 의존관계를 변경
    private DiscountPolicy discountPolicy; // 이 상태에서 바로 테스트시 NullpointException 발생
    // 해결하기위해 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야함.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
