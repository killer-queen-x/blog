package com.example.mini.controller;

import com.example.mini.ser.CommentService;
import com.example.mini.ser.GoodService;
import com.example.mini.ser.PostService;
import com.example.mini.ser.ReplyService;
import com.example.mini.xx.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
//@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @Autowired
    ReplyService replyService;
    @Autowired
    GoodService goodService;
    @RequestMapping("/list")
    public Object test(HttpServletRequest request){
        return postService.list();
    }
    @RequestMapping("/Home")
    public Object Home(){return postService.Home();}
    @RequestMapping("/Person")
    public Object Person(HttpServletRequest request,Integer uid){
        return postService.Person(uid);
    }
    @RequestMapping("/getDetail")
    public Object Detail(HttpServletRequest request,Integer pid){
        return postService.Detail(pid);
    }
    @RequestMapping("/Publish")
    public Boolean Publish(HttpServletRequest request,@RequestBody Post post){
        System.out.println(post);
        int count= postService.addPost(post);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("/DelPost")
    public boolean DelPost(HttpServletRequest request,Integer pid){
        commentService.RemoveComment(pid);
        replyService.RemoveReply(pid);
        goodService.RemoveGoods(pid);
        return postService.removeById(pid);
    }
    @RequestMapping("/CNotice")
    public Object CommentNotice(HttpServletRequest request,Integer uid){
        return postService.CommentNotice(uid);
    }

}
