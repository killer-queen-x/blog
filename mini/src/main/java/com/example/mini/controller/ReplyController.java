package com.example.mini.controller;

import com.example.mini.ser.ReplyService;
import com.example.mini.xx.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
//@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;
    @RequestMapping("/getReply")
    public Object getReply(HttpServletRequest request,Integer pid){
        return replyService.getReply(pid);
    }
    @RequestMapping("/AddReply")
    public boolean AddReply(HttpServletRequest request, @RequestBody Reply reply){
        System.out.println(reply);
        int count=replyService.AddReply(reply);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("/ReplyNotice")
    public Object ReplyNotice(HttpServletRequest request,Integer uid){
        return replyService.ReplyNotice(uid);
    }
    @RequestMapping("/getMyReply")
    public Object getMyReply(HttpServletRequest request,Integer uid){
        return replyService.getMyReply(uid);
    }
    @RequestMapping("/DelReply")
    public boolean DelReply(HttpServletRequest request,Integer rid){
        return replyService.removeById(rid);
    }
}
