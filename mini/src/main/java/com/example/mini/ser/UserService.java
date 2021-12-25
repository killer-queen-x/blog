package com.example.mini.ser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mini.xx.User;

import java.util.List;

public interface UserService extends IService<User> {
    //    登录
    public List<User> Login(String username, String password);
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
