package com.example.mini.ser.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mini.ma.CommentMapper;
import com.example.mini.ser.CommentService;
import com.example.mini.xx.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImp extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Comment> CommentList(Integer pid){
        return commentMapper.CommentList(pid);
    }
    @Override
    public int AddComment(Comment comment){
        return commentMapper.AddComment(comment);
    }
    @Override
    public List<Comment> MyComment(Integer uid){
        return commentMapper.MyComment(uid);
    }
    @Override
    public boolean RemoveComment(Integer pid){
        return commentMapper.RemoveComment(pid);
    }
    @Override
    public List<Comment> ComNotice(Integer uid){return commentMapper.ComNotice(uid);}
}
