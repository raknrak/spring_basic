package hello.core.singletone;

public class SingletonService {
    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    // static 영역에 객체 instance를 미리 하나 생성해 둔다.
    private static final SingletonService instatnce = new SingletonService();

    //2. public으로 알아서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    // 객체 instance가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다.
    // 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
    public static SingletonService getInstance() {

        return instatnce;

    }

    //3. 생성자를 private로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
