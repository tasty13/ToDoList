package com.mysite.todo.repository;

import com.mysite.todo.domain.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoEntity,Integer> {
    // 엔티티만으로는 데이터베이스에 데이터를 저장하거나, 조회할 수 없음, 데이터 처리를 위하여 JPA 리포지터리 필요.
    // Repository: Entity에 의해 생성된 DB에 접근하는 메서드들을 사용하기 위한 인터페이스
    // @Entity라는 어노테이션으로 DB구조를 만든 뒤 CRUD를 어케 할지 정의하는 계층

    // JpaRepository 인터페이스를 상속해서 리포지터리의 대상이 되는 엔티티 타입과 해당 엔티티의 PK 속성타입을 지정해야 함
}