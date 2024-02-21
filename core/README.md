# SpringBasic2
// <1>

SRP -> 단일 책임 원칙 
한 클래스는 하나의 책임만 갖는다. 
SRP를 따르면서 관심사를 분리, 구현 객체를 생성하고 연결하는 책임은 Config로 넘김 
클라이언트 객체는 실행하는 책임만 담당

DIP -> 의존관계 역전 원칙 
추상화에 의존 O , 구체화에 의존 X 
이전 코드를 살펴보면 OrderServiceImpl -> DiscountPolicy & FixDiscountPrice에 의존  
AppConfig가  FixDiscountPrice 객체 인스턴스를 클라이언트 코드 대신 생성, 클라이언트 코드에 의존관계를 주입 
DIP 해결 

IoC, DI 
IoC -> 제어의 역전 
클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성 & 연결 , 실행 
AppConfig로 구현 객체는 자신의 로직을 실행하는 역할만 담당. 제어권은 AppConfig가 가져간다. 

외부에서 관리하는 것을 제어의 역전 원칙 

DI -> 의존관계 주입
정적인 클래스 의존관계와 실행시점에 결정되는 동적인 객체 의존 관계는 분리해서 생각
실생 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달, 클라이언트와 실제 의존관계가 연결 되는 것을 의존관계 주입 
클라이언트 코들 변경 X -> 호출 하는 대상 타입 인스턴스 변경 가능 

정적 클래스 의존관계
import 코드만 보면 의존 관계 쉽게 판단 가능 
실제로 어떤 객체가 주입 될지는 알 수 없음 

동적 클래스 의존관계 
애플리케이션 실행 시점에 실제 생성된 객체 인스턴스가 참조가 연경된 의존관계 

IoC Container DI Container 
AppConfig = IoC Container or DI Container 

// <2>

AppConfig를 사용해서 직접 객체를 생성 DI, -> SpringContainer를 통해서 사용하도록 변경
@Configuration 붙은 AppConfig 구성, @Bean이라 적힌 메서드를 모두 호출, 반환된 객체를 SpringContainer에 담는다
이렇게 등록된 객체를 Spring Bean이라 부른다 

SpringContainer를 통해 필요한 SpringBean 객체를 찾아서 반환 이때, applicationContext.getBean() 메서드를 활용해서 찾을 수 있다. 

// <3>
ApplicationContext는 스프링 컨테이너이자 interface이다. 
스프링 컨테이너 생성 과정 
스프링 컨테이너 생성 -> 스프링 빈 등록 -> 스프링 빈 의존관계 준비 -> 스프링 빈 의존관계 설정 완료

// <4>
스프링빈 상속 관꼐 
부모 타입으로 조회하면 자식 타입도 함께 조회 
최고 부모인 Object 타입으로 조회하면 모든 스프링 빈 조회 

// <5>
BeanFactory와 ApplicationContext
BeanFactory > ApplicationContext > AnnotationConfig ApplicationContext 

BeanFactory -> 스프링 컨테이너의 최상위 인터페이스, 스프링 빈을 관리하고 조회, getBean을 제공

ApplicationContext -> BeanFactory 기능을 모두 상속 받아서 제공
MessageSource, EnvironmentCapable, ApplicationEventPublisher, ResourceLoader (interface)

