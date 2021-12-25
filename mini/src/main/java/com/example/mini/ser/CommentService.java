package com.example.mini.ser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mini.xx.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    public List<Comment> CommentList(Integer pid);
    public int AddComment(Comment comment);
    public List<Comment> MyComment(Integer uid);
    public boolean RemoveComment(Integer pid);
    public List<Comment> ComNotice(Integer uid);
}
