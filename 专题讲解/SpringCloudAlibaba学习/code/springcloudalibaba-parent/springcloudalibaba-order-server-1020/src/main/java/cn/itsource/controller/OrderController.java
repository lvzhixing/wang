package cn.itsource.controller;

import cn.itsource.dto.User;
import cn.itsource.feign.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @auth: wujiangbo
 * @date: 2022-02-13 12:53
 */
@RestController
public class OrderController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id){
        return userClient.getById(id);
    }
}