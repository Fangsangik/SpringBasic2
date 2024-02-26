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

// <8>
컴포넌트 스캔과 의존관계 자동 주입 시작 
스프링 빈을 등록할때 자바의 코드의 @Bean이나 XML의 <bean>등을 통해서 설정
스프링은 설정 정보가 없어도 자동을 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능이 존제
의존관계 자동 주입 해주는 @Autowired라는 기능 제공 

@ComponentScan을 사용하면 @Component가 붙은 모든 클래스를 스프링 빈으로 등록 
이대 스프링 빈의 기본 이름은 클래스명을 사용, 맨 앞글자만 소문자 사용 
@Autowired를 지정하면 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아 주입 = getBaen과 동일 

탐색 위치와 기본 scan 대상 
필요한 위치부터 탐색 할 수 있음 basePackages = " " -> 하위 패키지를 모두 탐색 

ComponentScan 기본 대상 
@Component : 컴포넌트 스캔에서 사용 
@Controller : 스프링 MVC 컨트롤러에서 사용  / 스프링 MVC 컨트롤러로 인식 
@Service : 스프링 비즈니스 로직에서 사용 / 비즈니스 계층을 인식 
@Repository : 스프링 데이터 넙근 계층에서 사용 / 데이터 계층 예외를 스프링 예외로 변환  
@Configuration : 스프링 설정 정보에서 사용 / 스프링 빈이 싱글톤을 유지 

Filter 
includeFilter : 컴포넌트 스캔 대상을 추가로 지정 
excludeFilter : 컴포넌트 스캔에서 제외 할 대상을 지정 

FilterType 옵션
ANNOTATION : 기본값 애노테이션을 인식 해서 동작 
ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작 
ASPECTJ : AspectJ 패턴 사용 
CUSTOM : TypeFilter 라는 인터페이스를 구현 
REGEX : 정규식 표현 

중복 등록 & 충돌 
자동 빈 등록 vs 자동 빈 등록 
컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록이 되는데 이름이 같은 경우 스프링은 오류를 발생 

수동 빈 등록 vs 자동 빈 등록 
수동이 우선권을 갖는다. 

// <9>
의존관계 자동 주입
다양한 의존관계 주입 방법 
1. 생성자 주입
2. 수정자 주입 
3. 필드 주입 
4. 일반 메서드 주입 

생성자 주입 
-> 생성자를 통해서 의존 관계를 주입 받는 방법 
   특징 => 생성자 호출 시점에 딱 1번만 호출 되는 것 보장, 불변 & 필수 의존관계에 사용 
   생성자가 2개 있을때는 @Autowired을 설정 
   생성자가 1개 있으면 @Autowired가 생략 (작동 됨)

수정자 주입
-> Setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입 
   선택 변경 가능성이 있는 의존관계에 사용 
   자바빈 프로퍼티 규역의 수정자 메서드 방식을 사용 

필드 주입 
-> 이름 그대로 필드에 바로 주입하는 방법 
   외부에서 변경이 불가능 하기 때문에 테스트 하기 힘듦 
   DI 프레임워크가 없으면 아무것도 못한다 
   사용 권장 X

일반 메서드 주입 
-> 일반 메서드를 통해서 주입 받을 수 있다 
   한번에 여러 필드를 주입 받을 수 있다
   일반적으로 사용 하지 않음 

옵션 처리 
주입할 스프링 빈이 없어도 동작할 때가 있음 -> @Autowired만 사용하면 required 옵션 기본값이 true -> 자동 주입 대상이 없으면 오류가 발생한다. 
따라서 
1. 자동 주입 할 대상이 없으면 수장자 메서드 자체가 호출이 안됨 (@Autowired(required = false))
2. @Nullable : 자동 주입 할 대상이 없으면 null 
3. optional<> : 자동 주입할 대상이 없으면 Optional.empty가 입력 

생성자 주입 권장 
불변 ->
대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점 까지 의존관계를 변경 할 일이 거의 없다 
대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다 (불변)
수정자 주입을 사용하면 setXxx 메서드를 public으로 열어두어야 한다 -> 좋은 설계는 아님. (수정 가능성 있음)

final 키워드 
생성자 주입을 사용하면 필드에 final 키워드를 사용 할 수 있다. 그런데 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.
참고로 수정자 주입은 -> final 사용할 수 없음 

// <10> 
롬복
@RequiredArgsConstructor 기능을 사용하면 final 붙은 필드를 모아서 생성자 자동 생성 

조회 대상 빈이 2개 이상일 때 해결 방법 
@Autowired 필드명 매칭 
@Qualifier -> @Qualifier끼리 매칭 -> 빈 이름 매칭
@Primary 사용 

@Autowired 타입 매칭을 시도, 여러 빈이 있으면 필드 이름, 파라미터 이름을 빈 이름을 추가 
필드명 매칭은 먼저 타입 매칭을 시도하고, 그 결과에 여러 빈이 있을 때 추가로 동작이 가능 

@Autowired 매칭 정리 
1. 타입 매칭 
2. 타입 매칭의 결과가 2개 이상일 때 필드명, 파라미터명으로 빈 이름 매칭 

@Qualifier 사용
@Qualifier는 추가로 구분자를 붙여주는 방법. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것은 아니다.
@Qualifier는 찾는 용도로만 

@Primary 사용
우선순위를 정하는 방법 @Autowired 시에 여러 빈이 매칭되면 @Primary가 우선순위를 갖는다.

@Primary VS @Qualifier 
@Qualifier의 단점은 사용마다 다 붙여주어야 한다.

메인 데이터베이스의 커넥션을 획득하는 스프링 빈은 Primary를 적용해서 조회 하는 곳에서 Qualifier 지정 없이 편리하게 조회 
서브 데이터베이스 커넥션 빈 획득 시에는 Qualifier를 지정해서 명시적으로 획득하는 방식으로 사용 
우선권은 상세히 동작하는 Qualifier가 더 높다 
애노테이션에는 상속이라는 개념이 없다. 여러 애노테이션을 모아서 사용하는 것은 스프링이 지원해주는 기능 

자동 수동의 올바른 기준 
수동 빈 등록 언제?
업무로직 빈 -> 웹을 지원하는 컨트롤러, 서비스, 리보지토리 (자동 선호)
기불 지원 빈 -> AOP를 처리, 데이터베이스 연결이나 공통 로그 처리 (수동 선호 => 명확성)