package com.example.mini.ser.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mini.ma.GoodMapper;
import com.example.mini.ser.GoodService;
import com.example.mini.xx.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodServiceImp extends ServiceImpl<GoodMapper, Good> implements GoodService {
    @Autowired
    GoodMapper goodMapper;
    @Override
    public List<Good> GetGoods(Integer pid){
        return goodMapper.GetGoods(pid);
    }
    @Override
    public int AddGood(Good good){
        return goodMapper.AddGood(good);
    }
    @Override
    public boolean DelGood(Integer pid,Integer uid){
        return goodMapper.DelGood(pid,uid);
    }
    @Override
    public boolean RemoveGoods(Integer pid){
        return goodMapper.RemoveGoods(pid);
    }
    @Override
    public List<Good> Live(Integer uid){return goodMapper.Live(uid);}
}
