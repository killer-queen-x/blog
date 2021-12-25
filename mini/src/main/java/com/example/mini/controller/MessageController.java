package com.example.mini.controller;

import com.example.mini.ser.MessageService;
import com.example.mini.xx.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
//    消息列表
    @RequestMapping("/Message")
    public Object Message(HttpServletRequest request, Integer uid){
        System.out.println(uid);
        return messageService.Message(uid);
    }
//    聊天记录
    @RequestMapping("/Chat")
    public Object Chat(HttpServletRequest request,String uid,String otherID){
        System.out.println(uid+otherID);
        return messageService.Chat(uid, otherID);
    }
//    发信息
    @RequestMapping("/Send")
    public boolean Send(HttpServletRequest request,@RequestBody Message message){
        int count=messageService.AddMessage(message);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
