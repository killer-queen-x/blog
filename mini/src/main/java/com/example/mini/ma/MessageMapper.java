package com.example.mini.ma;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mini.xx.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper extends BaseMapper<Message> {
    //    信息列表
    public List<Message> Message(Integer uid);

    //    聊天记录
    public List<Message> Chat(String uid,String otherID);
//    发信息
    public int AddMessage(Message message);
}
