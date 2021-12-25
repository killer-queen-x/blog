package com.example.mini.controller;

import com.example.mini.ser.UserService;
import com.example.mini.xx.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/Login")
    public Object Login(HttpServletRequest request,String username,String password){
        return userService.Login(username,password);
    }
    @RequestMapping("/Check")
    public Object Check(HttpServletRequest request,String username){
        System.out.println(username);
        return userService.Check(username);

    }
    @RequestMapping("/Register")
    public boolean Register(HttpServletRequest request,@RequestBody User user){
        System.out.println(user);
        int count= userService.Register(user);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/GetUser")
    public Object getUser(HttpServletRequest request,Integer uid){
        return userService.GetUser(uid);
    }
    @RequestMapping("/GetEdit")
    public Object GetEdit(HttpServletRequest request,Integer uid){
        return userService.GetEdit(uid);
    }
    @RequestMapping("/Update")
    public boolean Update(HttpServletRequest request,@RequestBody User user){
        int count=userService.Update(user);
        if (count>0){
            return true;
        }else {
            return false;
        }
    }

}
