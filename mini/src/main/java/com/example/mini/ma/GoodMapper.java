package com.example.mini.ma;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mini.xx.Good;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodMapper extends BaseMapper<Good> {

    public List<Good> GetGoods(Integer pid);
    public int AddGood(Good good);
    public boolean DelGood(Integer pid,Integer uid);
    public boolean RemoveGoods(Integer pid);
    public List<Good> Live(Integer uid);
}
