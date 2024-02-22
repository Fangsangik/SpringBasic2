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

// <6>
스프링컨테이너는 다양한 형식의 설정 정보를 받아드릴 수 있게 유연하게 설계 (XML, Java, Groovy)
BeanFactory > Application Context > Annotation Config ApplicationContext 

XML 설정 사용 
스프링 부트를 많이 사용하면서 XML 기반의 설정은 잘 사용 하지 않는다. 
아직 많은 레거시 프로젝트들이 XML로 되어 있다.

AnnotationCofigApplicationContext는 AnnotatedBeanDefinitionReader를 사용해서 AppConfig.class를 읽고,
BeanDefinition을 생성 

BeanDefinition 정보
BeanClassName : 생성할 빈 클래스 명 
factoryBeanName : 팩토리 역할 빈을 사용할 경우 이름 (appConfig)
factoryMethodName : 빈을 생성할 팩토리 메서드 저장 (memberService)
Scope : 싱글톤 
lazyInit : 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라 실제 빈을 사용할 때 까지 최대항 생성을 지연 
InitMethodName :  빈을 생성하고, 의존관계를 적용한 뒤 호출 되는 초기화 메서드 
DestroyMethodName : 빈 생명주기가 끝나서 제거하기 직전 호출 되는 메서드
Constructor arugments, Properties : 의존관계 주입에서 사용 

// <7>
싱글톤 패턴 
클래스의 인스턴스가 딱 1개만 생성 
객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 함
private 생성자를 사용해서 외부에서 임의로 new 키워드 사용 X 

static 영역에 객체 instance를 미리 하나 생성 
이 객체 인스터스 필요 -> getInstance 메서드를 통해서만 조회 가능 
딱 1개 객체 인스턴스만 존재, 생성자를 private으로 막아서 혹시 외부에서 new 키워드 객체 생성 제한

싱글톤 패턴을 형성하면 요청이 올때마다 새로 만드는 것이 아닌 이미 형성된 객체로 공유해서 효율적으로 사용 

싱글톤 패턴 사용 문제점
구현 해야 하는 코드 많아짐
클라이언트가 구체 클래스에 의존 -> DIP를 위반 
클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반 할 가능성이 있다. 
내부 속성을 변경하거나 초기화 어려움 
private 생성자로 자식 클래스 만들기 어려움 

싱글톤 컨테이너 
스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리 
컨테이너는 객체 하나만 생성 해서 관리 
스피링 컨테이너는 싱글톤 컨테이너 역할 -> 싱글톤 객체를 생성, 관리 = 싱글톤 레지스트리라고 한다.
스프링 컨테이너 덕분에 고객 요청이 올때마다 새로 객체를 생성하는 것이 아닌, 이미 형성된 객체를 공유해서 효율적으로 재사용 

싱글톤 방식 주의점
객체 인스턴스를 하나만 생성해서 공유, 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유, 싱글톤 객체 생태를 유지하게 설계 하면 안됨 

무상태를 유지
- 특정 클라이언트에 의존적 필드가 있으면 안된다. 
- 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다. 
- 읽기만 가능 
- 필드 대신 자바에서 공유 되지 않는 지역변수, 파라미더, ThreadLocal 등을 사용 
- 스프링 빈을 필드에 공유하면 장애 발생한다. 

@Configuration과 바이트코드조작
스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장
스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용 
출력해보면 xxxCGLIB가 붙이면서 상당히 복잡해진 것 출력, 스프링이 바이트코드 조작 라이브러를 사용해서 AppConfig 클래스를 상속받은 
임의이 다른 클래스를 만들고 다른 클래스를 스프링으로 등록 

@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환, 스프링 빈이 없으면 생성해서 
스프링 빈으로 등록, 반환하는 코드가 동적으로 생성 
만약 Configuration 적용하지 않고 사용하면, Bean만 적용할 경우 singleton 깨진다

