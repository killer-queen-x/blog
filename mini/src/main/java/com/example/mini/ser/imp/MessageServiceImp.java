package com.example.mini.ser.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mini.ma.MessageMapper;
import com.example.mini.ser.MessageService;
import com.example.mini.xx.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImp extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Override
    //    信息列表
    public List<Message> Message(Integer uid){
        return messageMapper.Message(uid);
    }
    //    聊天记录
    @Override
    public List<Message> Chat(String uid,String otherID){
        return messageMapper.Chat(uid,otherID);
    }
    @Override
    //    发信息
    public int AddMessage(Message message){
        return messageMapper.AddMessage(message);
    }
}
