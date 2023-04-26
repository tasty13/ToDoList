package com.mysite.todo.controller;

import java.util.List;

import com.mysite.todo.domain.ToDoEntity;
import com.mysite.todo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor    // ToDoRepository 속성을 포함하는 생성자를 자동으로 생성
@Controller // 주로 View 반환 위해 사용, @ResponseBody와 함께 사용 시 @RestController와 같은 기능 수행 가능
            // @RestController: JSON/XML 형태로 객체 데이터 반환 목적
public class ToDoController {   // Controller: 사용자 요철 처리 후 지정된 뷰에 모델객체 넘김
    private final ToDoService toDoService;

    @RequestMapping("/todo")
    public String list(Model model){
        List<ToDoEntity> toDoEntityList = this.toDoService.getList();
        model.addAttribute("toDoEntityList",toDoEntityList);
        return "todolist";
    }

    @RequestMapping("/")
    public String root(){   // 8080으로 진입할 경우 todolist로 redirect
        return "redirect:/todo";
    }

    @PostMapping("/todo/create")    // url 설정
    public String todoCreate(@RequestParam String content){
        // 아이템 삽입
        this.toDoService.create(content);   // service에서 만들어진 create 이용

        // 다시 원래 화면으로 리다이렉트
        return "redirect:/todo";
    }

    // 삭제 기능
    @DeleteMapping("/todo/delete/{id}") // 지우고자 하는 아이템의 아이디를 매개변수로 활용한다.
    public String todoDelete(@PathVariable Integer id){
        this.toDoService.delete(id);
        return "redirect:/todo";
    }

    // 수정 기능
    @PutMapping("/todo/update/{id}")
    public String todoUpdate(@RequestBody String content, @PathVariable Integer id){
        // ajax에서 put을 사용하려면 @RequestBody 어노테이션을 사용해야 한다.
        this.toDoService.update(id, content);
        return "redirect:/todo";
    }
}