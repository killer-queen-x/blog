package com.example.mini.ma;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mini.xx.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper extends BaseMapper<Post> {

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
