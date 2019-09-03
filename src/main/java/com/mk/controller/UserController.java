package com.mk.controller;

import com.mk.entity.User;
import com.mk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        userService.save(user);
        return "success";
    }


    @PostMapping("/delete")
    public String delete(String userName) {
        userService.delete(userName);
        return "success";
    }


    @PostMapping("/update")
    public String update(@RequestBody User user) {
        userService.updateById(user);
        return "success";
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping("/page")
    public Map<String, Object> pageList(Integer pageNum, Integer pageSize) {
        return userService.pageList(pageNum, pageSize);
    }


}
