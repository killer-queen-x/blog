package com.example.mini.ma;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mini.xx.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    public List<Comment> CommentList(Integer pid);

    public int AddComment(Comment comment);

    public List<Comment> MyComment(Integer uid);

    public boolean RemoveComment(Integer pid);
    public List<Comment> ComNotice(Integer uid);
}
