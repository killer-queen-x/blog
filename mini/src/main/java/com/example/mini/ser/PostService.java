package com.example.mini.ser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mini.xx.Post;

import java.util.List;

public interface PostService extends IService<Post> {
    //    首页
    public List<Post> Home();

    //    个人帖子
    public List<Post> Person(Integer uid);
    //    详情
    public List<Post> Detail(Integer pid);
    //    发表
    public int addPost(Post post);
    //    删除
    public int delPost(Integer pid);
    public List<Post> CommentNotice(Integer uid);
}
