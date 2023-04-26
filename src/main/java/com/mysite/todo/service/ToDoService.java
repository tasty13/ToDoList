package com.mysite.todo.service;

import java.util.List;

import com.mysite.todo.domain.ToDoEntity;
import com.mysite.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ToDoService {  // Service는 데이터 처리를 위해 작성하는 클래스
                            // 컨트롤러에서 리포지토리를 직접 사용할 수도 있지만 대규모 프로젝트는 서비스를 통해 사용
    private final ToDoRepository toDoRepository;    // Service 계층에서 Repository 이용해 데이터 관리 가능

    public List<ToDoEntity> getList(){  // repository에서 Entity 리스트 전부 가져옴
        return this.toDoRepository.findAll();
    }

    public void create(String content){ // 아이템을 새로 만들고 데이터베이스에 넣을 수 있도록 설정해준다.
        ToDoEntity toDoEntity = new ToDoEntity();   // Entity 불러와서 값 설정해줌
        toDoEntity.setContent(content);
        toDoEntity.setCompleted(false);
        this.toDoRepository.save(toDoEntity);       // repository에 저장
    }

    @Transactional
    public void delete(Integer id){
        ToDoEntity toDoEntity = toDoRepository.findById(id) // 리포지토리에서 id 찾음, 없을시 exception
                .orElseThrow(()->new IllegalArgumentException("해당 아이템이 없습니다. id=" + id));

        this.toDoRepository.delete(toDoEntity);
    }

    @Transactional
    public void update(Integer id, String content){
        ToDoEntity toDoEntity = toDoRepository.findById(id) // 리포지토리에서 id 찾음, 없을시 exception
                .orElseThrow(()->new IllegalArgumentException("해당 아이템이 없습니다. id=" + id));

        toDoEntity.setContent(content);         // 수정한 내용을
        this.toDoRepository.save(toDoEntity);   // repository에 저장
    }
}