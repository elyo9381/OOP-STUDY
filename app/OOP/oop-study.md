# 객체지향이란 ?

객체지향을 공부해보도록 하겠습니다. 

최범균 님의 인강을 참고하여 작성하였습니다.    
문제시 삭제하도록 하겠습니다. 

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

- 객체에서는 기능명세를 메소드를 이용해서 기능명세를 합니다


기능 명세는 메서드(오퍼레이션)를 이용해서 기능 명세 합니다.

- 객체와 객체는 기능을 사용해서 연결
  - 기능 사용 = 메서드 호출

- 메서지 : 객체와 객체 상호 작용 : 메시지를 주고 받는다고 표현
  - 메서드를 호출하는 메시지, 리턴하는 메시지 , 익셉션 메시지

### 캡슐화

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

# 캡슐화 예제 1

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

# 캡슐화 예제 2

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

# 캡슐화 예제 3

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

# 캡슐화 예제 4

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