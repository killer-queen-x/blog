package com.example.mini.controller;

import com.example.mini.ser.GoodService;
import com.example.mini.xx.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class GoodController {
    @Autowired
    GoodService goodService;
    @RequestMapping("/Live")
    public Object Goods(HttpServletRequest request,Integer uid){
        return goodService.Live(uid);
    }
    @RequestMapping("/GetGoods")
    public Object GetGoods(HttpServletRequest request,Integer pid){
        return goodService.GetGoods(pid);
    }
    @RequestMapping("/AddGood")
    public boolean AddGood(HttpServletRequest request,@RequestBody Good good){
        System.out.println(good);
        int count=goodService.AddGood(good);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("/DelGood")
    public boolean DelGood(HttpServletRequest request,Integer pid,Integer uid){
        return goodService.DelGood(pid,uid);
    }
}
