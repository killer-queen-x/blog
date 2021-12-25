package com.example.mini.ma;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mini.xx.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
//    登录
    public List<User> Login(String username,String password);
//    注册
    public List<User> Check(String username);
    public int Register(User user);
    //    获取用户信息
    public List<User> GetUser(Integer uid);
    //    获取可编辑用户信息
    public List<User> GetEdit(Integer uid);
//    修改
    public int Update(User user);
}
