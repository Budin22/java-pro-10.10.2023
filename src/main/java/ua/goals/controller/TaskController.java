package ua.goals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/")
    public String getTask(){
        return "My task";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable Long id){
        if(id > 3) throw  new RuntimeException("More then 3");
        return "My task #" + id;
    }
}
