package com.mysite.todo.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter // @Getter와 @Setter는 Lombok(어노테이션 기반으로 코드를 자동완성 해주는 라이브러리)에셔 사용하는 라이브러리
@Setter // 예를 들어 xxx라는 필드에 선언하면 자동으로 getXxx()(boolean 타입: isXxx())와 setXxx() 메소드를 생성
        // 필드 레벨이 아닌 클래스 레벨에 선언 시 모든 필드에 접근자와 설정자가 자동으로 생성

@Entity // 스프링에서 JPA 사용시 @Entity 어노테이션 붙여 Entity 클래스임을 명시 가능,
        // @Controller 처럼 @Entity라고 작성해야한다.

public class ToDoEntity {
    @Id // 기본키로 설정한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 데이터를 저장할 때 해당 속성에 따로 값을 셋팅하지 않아도 1씩 자동으로 증가하도록 함
    // strategy는 고유 번호를 생성하는 옵션
    // GenerationType.IDENTITY는 해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용한다.
    private Integer id;

    @Column(length=200) // id가 아닌 다른 칼럼에는 @Column 붙여 표현할 수 있음
    // Column의 세부 설정을 위해 사용
    private String content; // 투두

    @Column(nullable = false)
    private Boolean completed;  // 할일 완료했는지 체크
}