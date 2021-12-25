package com.example.mini.ser.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mini.ma.UserMapper;
import com.example.mini.ser.UserService;
import com.example.mini.xx.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    //    登录
    public List<User> Login(String username, String password){
        return  userMapper.Login(username,password);
    }
    //    注册
    @Override
    public List<User> Check(String username){
        return userMapper.Check(username);
    }
    @Override
    public int Register(User user){
        return userMapper.Register(user);
    }
    @Override
    //    获取用户信息
    public List<User> GetUser(Integer uid){
        return  userMapper.GetUser(uid);
    }
    @Override
    //    获取可编辑用户信息
    public List<User> GetEdit(Integer uid){ return  userMapper.GetEdit(uid);}
    @Override
    //    修改
    public int Update(User user){return userMapper.Update(user);}
}
