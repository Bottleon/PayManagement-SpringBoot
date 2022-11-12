# PayManagement-SpringBoot

사용이유
==============
Object Validate Annotation @NotNull vs @Column(nullable=false)
--------------
***
*@NotNull*
: Request Json Data를 Object로 변환시키는 과정에서 검사(Controller)

*@Column(nullable=false)*
: Service에서 DB에 접근할 때 검사

공통점 : 둘다 DDL create auto로 table만들면 column들 not null 속성 부여

이런 차이점에서 조금 더 빨리 검사가 가능한 @NotNull 사용