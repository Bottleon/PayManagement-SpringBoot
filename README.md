

## 아르바이트 수당종류
1. 연장수당(연장근로수당)
2. 야간수당
3. 휴일수당
4. 주휴수당

예외사항 : 상시근로자 수 **5인 미만** 사업장은 1,2,3번 해당 X 주휴수당은 **상시근로자 수와 관계없음**

## 연장 근로수당
연장근무, 야간근무, 휴일근무 모두 연장근로에 해당
1. 연장근무
: 1일 8시간 이상 또는 1주 40시간 이상 근무
2. 야간근무
: 22시부터 06시 사이 근무
3. 휴일근무
: 근로계약서상 근무일이 아닌 휴일로 정해진 날에 근무

EX) 시급 : 10000원, 휴일 근무시간 : 10시간
계산법 : (8시간X10000원X150%) + (2시간X10000원X200%) = 160,000원 

* 해당 근무들이 동시에 이루어질 때는 중복 지급

## 사용이유
### Object Validate Annotation @NotNull vs @Column(nullable=false)
***
*@NotNull*
: Request Json Data를 Object로 변환시키는 과정에서 검사(Controller)

*@Column(nullable=false)*
: Service에서 DB에 접근할 때 검사

공통점 : 둘다 DDL create auto로 table만들면 column들 not null 속성 부여

이런 차이점에서 조금 더 빨리 검사가 가능한 @NotNull 사용
