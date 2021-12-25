package com.example.mini.ser.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mini.ma.ReplyMapper;
import com.example.mini.ser.ReplyService;
import com.example.mini.xx.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReplyServiceImp extends ServiceImpl<ReplyMapper,Reply> implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;
    @Override
    public List<Reply> getReply(Integer pid){
        return replyMapper.getReply(pid);
    }
    @Override
    public int AddReply(Reply reply){
        return replyMapper.AddReply(reply);
    }
    @Override
    public boolean RemoveReply(Integer pid){
        return replyMapper.RemoveReply(pid);
    }
    @Override
    public List<Reply> ReplyNotice(Integer uid){
        return replyMapper.ReplyNotice(uid);
    }
    @Override
    public List<Reply> getMyReply(Integer uid){
        return replyMapper.getMyReply(uid);
    }
}
