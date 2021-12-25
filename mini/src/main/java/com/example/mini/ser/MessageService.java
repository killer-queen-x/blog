package com.example.mini.ser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mini.xx.Message;

import java.util.List;

public interface MessageService extends IService<Message> {
//    信息列表
    public List<Message> Message(Integer uid);
//    聊天记录
    public List<Message> Chat(String uid,String otherID);
    //    发信息
    public int AddMessage(Message message);
}
