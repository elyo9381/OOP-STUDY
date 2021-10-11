# 객체지향이란 ?

객체지향을 공부해보도록 하겠습니다.

# 객체지향

소프트웨어 산업에서 코드의수는 매우 중요합니다.

하지만 버전을 출시하는 동시에 코드의수가 늘어난다면 다양한 문제가 발생합니다.

유지비용이 늘어난다라든지, 개발에 있어서 프로그램분석이 오래걸린다거나 등등. 많은 어려움이 존재합니다.

그렇기 때문에 객체지향적이라는 방법이 필요합니다.

비용과 변화를 줄이기 위한 방법론으로는 아래와 같은 방법이 존재합니다.

- 패러다임
  - 객체지향, 함수형, 리액티브
- 코드, 설계, 아키텍처
  - TDD,SOLID,DDD
  - 클린 아키텍처, MSA
- 업무 프로세스/문화
  - 애자일,DevOps


우와같은 방법론들에서 기본이 되는 객체지향을 살펴 보도록 하겠습니다.

# 1.객체

### 절차지향

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled.png)

- 데이터를 공유하는 방식입니다.
- 이러한 방법은 시간이 흘러갈수록 데이터가 복잡하게 진행되어 코드분석이 어려워집니다.
- 하나의 함수는 하나의 기능만 내포 해야하는데 이를 무시하게 됩니다.

→ 시간이 지날수록 코드가 어려워 집니다.

### 객체지향

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%201.png)

- 데이터와 프로시저를 객체라는 단위로 묶어 사용하는 방법입니다.
- 다른 A객체는 다른 B객체를의 데이터를 접근할수없습니다. (접근방법에 의해서)
- 객체의 핵심 → 기능제공
  - 객체는 제공하는 기능으로 정의


객체는 기능명세가 필요합니다.

기능 명세는 메서드(오퍼레이션)를 이용해서 기능 명세 합니다.

- 객체와 객체는 기능을 사용해서 연결
  - 기능 사용 = 메서드 호출

- 메서지 : 객체와 객체 상호 작용 : 메시지를 주고 받는다고 표현
  - 메서드를 호출하는 메시지, 리턴하는 메시지 , 익셉션 메시지

## 캡슐화

- 데이터 + 관련 기능 묶기
- 객체가 기능을 어떻게 구현했는지 외부에 감추는 것
  - 구현에 사용된 데이터의 상세 내용을 외부에 감춤
- 정보은닉 의미 포함
- 외부에 영향 없이 객체 내부 구현 변경 가능

### 캡슐화하지 않으면

- 요구사항의 변화가 데이터구조/사용에 변화를 발생시킴
- 해당 데이터를 사용하는 코드의 수정 발생

### 캡슐화 하면

- 기능을 제공하고 구현 상세를 감춤

### 캡슐화를 위한 규칙

- Tell, Don't Ask
  - 데이터 달라 하지 말고 해달라고 하기

    ```java
    if(acc.getMembership() == REGULAR){
    .. 정회원 기능 
    }
    ////////////
    if(acc.hasRegularPermission()){
    	.. 정회원 기능
    }
    ```

- Demeter's Law : 중복된 내용을 객체를 호출하지 말고 하나의 기능의 메소드로 만들어라
  - 메서드에서 생성한 객체의 메서드만 호출
  - 파라미터로 받은 객체의 메서드만 호출
  - 필드로 참조하는 객체의 메서드만 호출


캡슐화 정리

- 캡슐화 : 기능의 구현을 외부에 감춤
- 캡슐화를 통해 기능을 사용하는 코드에 영향을 주지 않고 내부구현을 변경할 수 있는 유연함

### 캡슐화 예제 1

```java
public AuthResult authenticate(String id, String pw){
	Member mem = findOne(id);
	if (mem == null) return AuthResult.NO_MATCH;

	if( mem.getVerificationEmailStatus() != 2){
		return AuthResult.NO_EMAIL_VERIFIED;
	}
	if(passwordEncoder.isPasswordValid(mem.getPassword(),pw, mem.getId()){
		return AuthResult.SUCCESS;
	}
	return AuthResult.NO_MATCH;
}
```

위와 같은 코드를 캡슐화 해보자

우리는 2가지 방법론을 알고있다. 이는 Tell,Don't Ask 와  Demeter's Law이다.

위의 코드를 보면 2번째 조건문에서 mem 객체의 값을 불러와 비교 하여 확인함을 알수 있다.

이는 Don's ask에 위배 되는 규칙이라고 볼수있다. 그렇기 때문에 이를 mem 객체의 메소드로 캡슐화 가능하다.

```java
public AuthResult authenticate(String id, String pw){
	Member mem = findOne(id);
	if (mem == null) return AuthResult.NO_MATCH;

	if( !mem.isVerifiedEmail() ){
		return AuthResult.NO_EMAIL_VERIFIED;
	}
	if(passwordEncoder.isPasswordValid(mem.getPassword(),pw, mem.getId()){
		return AuthResult.SUCCESS;
	}
	return AuthResult.NO_MATCH;
}

///////

public class Member{
	private int verificationEmailStatus;

	public boolean isEmailVerified(){
		return verficationEmailStatus == 2;
	}
...
}
```

### 캡슐화 예제 2

```java
public class Rental{
	private Movie movie;
	private int daysRented;
	
	public int getFrequentRenterPoint(){
		if( movie.getPriceCode() == Movie.MEW_RELEASE && daysRented > 1)
			return 2;
		else 
			return 1;
....

/////////////

public class Movie { 

	public static int REGULAR = 0;
	public static int NEW_RELEASE = 1;
	private int priceCode;

	public int getPriceCode() {
		return priceCode;
	}
	....
}
```

위의 두 클래스를 확인하자

getFrequentRenterPoint()는 movie에서 특정 코드를 불러와 확인하고 daysRented라는 조건을 만족해야 특정 값을 리턴한다.

movie에서 특정값을 불러온다는것은 dont's ask를 위배하는 행위이다. 그러므로 이를 movie로 바꿔보자

특정 메소드로 바꾼다 하더라도 결국 또 movie에서 생성한 특정 메소드를 불러와야 함은 동일하다.

그렇기 때문에 daysRented 변수를 파라미터화 하여 하나의 메소드로 통일시키도록 하자

```java
public class Rental{
	private Movie movie;
	private int daysRented;
	
	public int getFrequentRenterPoint(){
		return  movie.getFrequentRenterPoint(daysRented);
	}
....

/////////////

public class Movie { 

	public static int REGULAR = 0;
	public static int NEW_RELEASE = 1;
	private int priceCode;

	public int getPriceCode() {
		return priceCode;
	}

	public int getFrequentRenterPoint(int daysRented){
			if( this.isValidPriceCode && daysRented > 1)
		//if( this.priceCode && daysRented > 1)
			return 2;
		else 
			return 1;
	}

	private boolean isVaalidPriceCode(){
		movie.getPriceCode() == Movie.MEW_RELEASE 
	}

	....
}
```

### 캡슐화 예제 3

```java

Timer t = new Timer();
t.startTime = System.currentTimeMillis();

...

t.stopTime = System.currnetTimeMillis();
long elaspedTime = t.stopTime - t.startTime;
....

/////////////

public class Movie { 
	
	public long startTime;
	public long stopTime;
	....
}
```

위의 두 클래스를 확인하자

start, stop의 기능을 통해서 특정 시간을 알고 싶은 기능이다dlek.

시간을 구하는 코드들을 보면 Timer()를 공통적으로 사용함을 알수있다.

여기서 Millis의 시간뿐만 아니라 nano 시간, 초단위 등등을 알고 싶다면 추가적인 코드를 작성해야한다.

demeter 법칙을 통해 하나의 메소드로 만들면 캡슐화가 잘 진행 될것이라고 생각한다.

```java
Timer t = new Timer();
t.start()

...

t.stop()
long time = t.calculateTime(MILLISECOND)
....

/////////////

public class Timer { 
	
	private long startTime;
	private long stopTime;

	public void start(){
		this.startTime() = System.currentTimeMillis();	
	}	

	public void stop(){
		this.stopTime() = System.currentTimeMillis();	
	}	

	public long calculateTime(TimeUnit unit){
		switch(unit) {
			case MILLISECOND:
				return stopTime - startTime;
	}
	....
}
```

이와 같이 진행하면 파라미터를 통해서 다양한 조건을 받을수있으므로 객체는 함수구현 코드 변경으로 다양하게 코드를 조작 할수 있게 된다.

### 캡슐화 예제 4

```java
public void verifyEmail(String token){
	Member mem = findByToken(token);
	if(mem == null) throw new BadTokenException();
	
	if(mem.getVerificationEmailStatus() == 2) {
		throw new AlreadyVerifiedException();
	} else {
		mem.setVerificationEmailStatus(2);
	}
	//
```

위의 코드는 ask 부분을 통해 2가지 기능을 제공한다.

하나는 새로운 익셉션 생성 기능이고 하나는 데이터 업데이트 기능이다.

이것을 나누기에 적절하지 않다면 캡슐화를 고려 해볼수있다. ask부분이 존재하므로 이를 don't ask하고 동시에demeter가 진행되는것이다.

→ 이때 두 기능을 내포하는 함수명을 작성한다.

```java
public void verifyEmail(String token){
	Member mem = findByToken(token);
	if(mem == null) throw new BadTokenException();
	
	mem.verifyEmail();
////////////

public class Member {
	private int verificationEmailStatus;

	public boolean isEmailVerified(){
		return verificationEmailStatus == 2;
	}

	public void verifyEmail(){

		if(isEmailVerified()) {
			throw new AlreadyVerifiedException();
		} else {
			mem.setVerificationEmailStatus(2);
		}
	}

...
}

```

이와 같이 진행하면 파라미터를 통해서 다양한 조건을 받을수있으므로 객체는 함수구현 코드 변경으로 다양하게 코드를 조작 할수 있게 된다.

# 2. 다형성과 추상화

## 다형성

### 다형성 (Polymorphism)

- 여러 모습을 갖는것
- 객체 지향에서는 한 객체가 여러 타입을 갖는것
  - 즉 한 객체가 여러 타입의 기능을 제공
  - 타입 상속으로 다형성 구현

```java
public class Timer {
	public void start() {...}
	public void stop() {...}
}

public interface Rechargeable { 
	void charge();
}
```

위와같은 클래스와 인터페이스를 상속 받은 IoTTimer 클래스가 존재한다고 가정해보자

```java
public class IotTimer extends Timer
interface Rechargeable{
	public void charge(){
	...
	}
}
```

이렇게 클래스와 인터페이스를 모두 상속받을수 있습니다.

IotTimer는 클래스와 인터페이스를 모두 상속하기 때문에

IotTimer 인스턴스는 Timer, Recahrgeable의 타입에 모두 할당할 수 있습니다.

→ IotTimer는 Timer의 기능, Rechargeable의 기능을 사용할수있다는 뜻입니다.

⇒ 이런 다형성을 제공하는 이유는 추상화를 하기 위해서 입니다.

## 추상화

### 추상화( Abstraction)

- 데이터나 프로세스 등을 의미가 비슷한 개념이나 의미 있는 표현으로 정의하는 과정
- 두 가지 방식의 추상화
  - 특정한 성질, 공통 성질(일반화)

서로 다른 구현 추상화

- scp로 파일 업로드
- HTTP 로 데이터 전송
- DB 테이블에 삽입

→ 다음 기능들의 공통점은  테이터를 삽입하기 위해서 푸시 발송 요청한다는 점입니다.

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%202.png)

### 추상화 하는 시점

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%203.png)

→ 요구사항 변경이나 확장이 필요한 시점에 추상화가 필요합니다.

### 추상화를 잘하려면

- 구현을 한 이유가 무엇 때문인지 생각해야함

# 3. 상속보단 조립

## 상속과 재사용

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%204.png)

위와 같은 계층 구조는 상속을 통해서 기능 재사용을 하고 있습니다.

이러한 구조는 문제가 존재합니다. (단점)

- 상위 클래스 변경 어려움
  - 상위클래스가 변경되면 모든 하위 클래스의 영향을 끼치게 됩니다. (비정상적인 실행 발생)
  - 상위 클래스는 어떤 하위 클래스가 추가 될지 모릅니다.
- 클래스 증가
  - 다중 상속을 받야아하는 클래스가 존재할시 (새로운 조합) 하위클래스가 증가하게 됩니다.
- 상속 오용
  - 특정 A클래스에서  특정 B를 상속받을때 (B는 Collection,..ETC 특정 클래스)
  - A클래스에서는  B의 클래스까지 메소드 조회가 되고 이는 A의 메소드만 보여주지 않아 사용에 혼선이 생깁니다.

→ 이러한 문제의 해결방법은 상속 대신 컴포지션(조립)을 이용하는 것입니다.

- 여러 객체를 묶어서 더 복잡한 기능을 제공
- 보통 필드로 다른 객체를 참조하는 방식으로 조립 또는 객체를 필요 시점에 생성/구함

```java
public class FlowController {
	private Encryptor encryptor = new Encryptor(); // 필드로 조립

	public void process(){
		....
		byte[] encryptedData = encryptor.encrypt(data);
		....
	}
}
```

# 4. 기능과 책임 분리

## 기능분해

- 기능은 하위 기능으로 분해 가능합니다.

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%205.png)

기능 분해를 할때 클래스가 크거나, 메소드가 커지면 큰 클래스,메소드에 여러기능이 존재하게 됩니다.

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%206.png)

### 책임을 분리하는 방법

- 패턴적용

  ![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%207.png)

- 계산 기능 분리
  - 빨간 부분의 계산 부분을 새로운 클래스로 만들어 계산클래스를 만드는 방법입니다.

  ![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%208.png)

- 외부 연동 분리
  - 네트워크,메시징,파일등 연동 처리 코드 분리

  ![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%209.png)

- 조건별 분기는 추상화
  - 연속적인 if-else 추상화 고민

  ![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2010.png)


→ 주의 :  의도가 잘 드러나는 이름 사용 !!!

역할 분리의 장점

- 테스트 용이

## 분리 연습

### 예제 1

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2011.png)

위의 코드는  암호화, 복호화 부분이 복잡하고 post의 여러기능이 존재하고  같은 책임을 가지고 있습니다.

그리고 restTemplate 또한 여러곳에서 사용할수있고 책임이 여러가지 존재할 수 있습니다.

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2012.png)

이를 Cyper 클래스를 생성해서 책임을 분리 할수있습니다.

이렇게 코드를 작성하면 조금더 명확한 의미를 전달할수 있습니다.

### 예제 2

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2013.png)

위의 코드는 앞선 예제에서 tell, dont's ask 와 Demeter 법칙을 통해서 캡슐화 하였는데 이를 더 추상화(리팩터링) 한다면

추상클래스(인터페이스)를 이용해서 추상화 할수 있습니다.

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2014.png)

### 예제 3

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2015.png)

기능을 중심으로 책임을 구별할수있습니다.

위의 예제는 큰 기능을 중점으로 서브 기능을 나눈 케이스입니다.

객체는 기능 중심으로 이루어 져야 하고 이를 통해서 명확한 의도를 전달할 수 있습니다. ( 추상화가 가능하기 때문)

이러한 분리된 기능들을 객체들이 책임을 가져야합니다.

하나의 클래스(메소드) 하나의 기능만 수행해야하기 때문입니다. (객체지향의 가장 큰 원칙)

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2016.png)

# 의존과 DI

## 의존

- 기능 구현을 위해 다른 구성 요소를 사용하는 것
  - 의존의 예: 객체생성, 메서드 호출, 데이터 사용
- 의존은 변경이 전파될 가능성을 의미
  - 의존하는 대상이 바뀌면 바뀔 가능성이 높아짐
    - 예: 호출하는 메서드의 파라미터가 변경
    - 예: 호출하는 메서드가 발생할 수 있는 익셉션 타입이 추가

- 순환의존 : 각 클래스가 다른 클래스를 참고하고 이것이 cycle이 생기는 현상

### 의존 대상 많을 때 1, 기능 많은 경우

- 한 클래스에서 많은 기능을 제공하는 경우

```java
public class UserService {

	public void regist(RegReq regReq) {
		...
	}
	public void changPw(ChangeReq chgReq) {
		...
	}
	public void blockUser(String id, String reason) {
		...
	}
}
```

위와 같은 경우

각 메소드를 위한 테스트를 작성할때 UserService 에대한 의존성이 생깁니다.

또한 blockUser의  changePw를 테스트한다는 시나리오가 존재할때 두 메소드는 서로 의존성이 생기므로 테스트가 어렵습니다. ( 둘중 하나를 초기화 하여 값을 설정해야 하므로 )

단점

- 각  기능마다 의존하는 대상이 다를 수 있음
- 한 기능 변경이 다른 기능에 영향을 줄 수  있음

해결방법

- 기능 별로 분리 고려

→ 기능별로 클래스 분리하는것이 좋다.

→ 클래스가 늘어나지만 의존성이 줄어듭니다.

### 의존대상 많을때 2, 묶어보기

- 몇가지 의존 대상을 단일 기능으로 묶어서 생각해보면 의존 대상을 줄일 수 있음

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2017.png)

### DI 의 장점

- 상위 타입을 사용할 경우 의존 대상이 바뀌면 조립기 (설정)만 변경하면 됨
- 의존하는 객체 없이 대역 객체를 사용해서 테스트 가능

# DIP

## 고수준 모듈, 저수준 모듈

### 고수준 모듈

- 의미 있는 단일 기능을 제공
- 상위 수준의 정책 구현
- ex)  도메인 기능, service등등

### 저수준 모듈

- 고수준 모듈의 기능을 구현하기 위해 필요한 하위 기능의 실제 구현
- ex) 실제 CRUD기능 ( repository, calculation )

고수준이 저수준에 직접 의존하면 ???

고수준 정책은 바뀌지 않으나 저수준 구현 변경으로 많은 코드 변경이 발생합니다.

### DIP : 의존 역전 원칙

- 고수준 모듈은 저수준 모듈의 구현에 의존하면 안됨
- 저수준 모듈이 고수준 모듈에서 정의한 추상타입에 의존해야함

![Untitled](%E1%84%80%E1%85%A2%E1%86%A8%E1%84%8E%E1%85%A6%E1%84%8C%E1%85%B5%E1%84%92%E1%85%A3%E1%86%BC%E1%84%8B%E1%85%B5%E1%84%85%E1%85%A1%E1%86%AB%206a6cc8ea095e401e843c149753a8b5a5/Untitled%2018.png)

요구사항과 업무에대한 이해가 높아지면서 DIP가 진행됩니다.