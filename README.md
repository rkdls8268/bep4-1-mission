## DDD & MSA

### DDD
* Domain Driven Design: 도메인 주도 설계
* 업무 규칙을 팀의 언어로 경계 안에 모아두는 것

### 주요 개념
* bounded context
  * 최대한 독립적이고 폐쇄적인 모듈
  * 바운디드 컨텍스트로 나뉘면 각 모듈을 이해하기 쉬워진다. 즉, 유지보수 하기가 쉬워진다!
  * 결합도를 낮추고 응집도를 높이자
* 유비쿼터스 언어
  * 같은 bounded context 내에서는 같은 이름을 쓸 것
  * 현업(실무담당자)이 사용하는 언어를 사용해야 한다.
  * ex. 상품을 정의할 때 product, goods 등 개념이 혼동되면 안됨.
* 규칙은 한 곳에 모으기! 즉, 업무 규칙이 바뀌면 해당 bounded context 만 고치게 만들기

### 요약
DDD란 결합도는 낮추고 응집도를 높일 수 있도록 각 도메인별로 최대한 독립적이고 폐쇄적으로 나누어 설계하는 소프트웨어 설계 방법


### spring event 를 활용하여 bounded context 간 결합도 분리
* event class: 이벤트를 처리하는데 필요한 데이터
* event publisher: ApplicationEventPublisher 빈을 주입하여 publishEvent() 메서드를 통해 생성된 이벤트 객체를 넣어줌
* event listener: @EventListener 어노테이션을 통해 발생하는 이벤트 캐치