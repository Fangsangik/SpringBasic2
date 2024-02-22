package hello.core.singleton;

public class SingletonService {
    //1. static 영역 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면, 이 statci 메서드를 통해서만 조회
    public static SingletonService getInstance() {
        return instance; //instance에 참조를 꺼낼 수 O
    }

    //3. 생성자를 private으로 설정해서 외부에서 new라는 키워드 사용 X
    //new라는 keyWord instance 생성X -> 다른 곳에서 저장
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출 ");
    }
}
