package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig { // 애플리케이션의 실제 동작에 필요한 구현 객체를 생성 -> DI Container

    //인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)
    // MemberServiceImpl -> MemoryMemberRepository
    // 리팩토링 전
   /* public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }*/
    //인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)
    // OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
// 리팩토링 전
  /*  public OrderService orderService(){
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPolicy());
    }
}*/
// AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
// MemberServiceImpl
// MemoryMemberRepository
// OrderServiceImpl
// FixDiscountPolicy
//
// 객체의 생성과 연결은 AppConfig가 담당한다.
// DIP 완성 : MemberServiceImpl 은 MemberRepository 인 추상에만 의존함녀 된다. 이제 구체 클래스를 몰라도 된다.
// 관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
//
// AppConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
// 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서
// DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라고 한다.
// --------------------------------------------------------
// 리팩토링 후
    //new MemoryMemberRepository() 의 중복 제거
    //
    @Bean
    public MemberService memberService(){
        // AppConfig 호출로그
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        // AppConfig 호출로그
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());

    }
    @Bean
    public MemberRepository memberRepository(){
        // AppConfig 호출로그
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 할인 정책 변경
    }
}