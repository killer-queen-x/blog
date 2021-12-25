package com.example.mini.controller;

import com.example.mini.ser.CommentService;
import com.example.mini.xx.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
//@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping("/getComment")
    public Object CommentList(HttpServletRequest request,Integer pid){
        return  commentService.CommentList(pid);
    }
    @RequestMapping("/AddComment")
    public boolean AddComment(HttpServletRequest request, @RequestBody Comment comment){
        System.out.println(comment);
        int count=commentService.AddComment(comment);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("/MyComment")
    public Object MyComment(HttpServletRequest request, Integer uid){
        return commentService.MyComment(uid);
    }
    @RequestMapping("/DelComment")
    public boolean DelComment(HttpServletRequest request,Integer cid){
        return commentService.removeById(cid);
    }
    @RequestMapping("/CommentNotice")
    public Object ComNotice(HttpServletRequest request,Integer uid){
        return commentService.ComNotice(uid);
    }
}
