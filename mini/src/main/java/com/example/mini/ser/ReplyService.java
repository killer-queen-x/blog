package com.example.mini.ser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mini.xx.Reply;

import java.util.List;

public interface ReplyService extends IService<Reply> {
    public List<Reply> getReply(Integer pid);
    public int AddReply(Reply reply);
    public boolean RemoveReply(Integer pid);
    public List<Reply> ReplyNotice(Integer uid);
    public List<Reply> getMyReply(Integer uid);
}
