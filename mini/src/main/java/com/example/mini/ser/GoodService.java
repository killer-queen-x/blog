package com.example.mini.ser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mini.xx.Good;

import java.util.List;

public interface GoodService extends IService<Good> {
    public List<Good> GetGoods(Integer pid);
    public int AddGood(Good good);
    public boolean DelGood(Integer pid,Integer uid);
    public boolean RemoveGoods(Integer pid);
    public List<Good> Live(Integer uid);
}
