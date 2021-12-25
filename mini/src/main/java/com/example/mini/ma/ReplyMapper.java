package com.example.mini.ma;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mini.xx.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyMapper extends BaseMapper<Reply> {
    public List<Reply> getReply(Integer pid);
    public int AddReply(Reply reply);
    public boolean RemoveReply(Integer pid);
    public List<Reply> ReplyNotice(Integer uid);
    public List<Reply> getMyReply(Integer uid);
}
