package com.example.mini.ser.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mini.ma.PostMapper;
import com.example.mini.ser.PostService;
import com.example.mini.xx.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImp extends ServiceImpl<PostMapper, Post>  implements PostService{
    @Autowired
    PostMapper postMapper;
    @Override
    //    首页
    public List<Post> Home(){
        return postMapper.Home();
    }

    //    个人帖子
    public List<Post> Person(Integer uid){
        return postMapper.Person(uid);
    }
    @Override
    //    详情
    public List<Post> Detail(Integer pid){
        return postMapper.Detail(pid);
    }
    @Override
    //    发表
    public int addPost(Post post){
        return postMapper.addPost(post);
    }
    @Override
    //    删除
    public int delPost(Integer pid){
        return postMapper.delPost(pid);
    }
    public List<Post> CommentNotice(Integer uid){
        return postMapper.CommentNotice(uid);
    }
}
