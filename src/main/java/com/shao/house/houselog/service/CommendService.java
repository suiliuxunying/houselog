package com.shao.house.houselog.service;

import com.shao.house.houselog.mapper.CommendMapper;
import com.shao.house.houselog.model.Commend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommendService {
    @Autowired
    private CommendMapper commendMapper;

    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }


    public int insert(Commend record) {
        return 0;
    }


    public int insertSelective(Commend record) {
        return commendMapper.insertSelective(record);
    }


    public Commend selectByPrimaryKey(Integer id) {
        return null;
    }


    public int updateByPrimaryKeySelective(Commend record) {
        return 0;
    }


    public int updateByPrimaryKey(Commend record) {
        return 0;
    }

    public  void  deleteAll() {
        commendMapper.deleteAll();
    }

    public List<Integer> getCommendList(Integer uid){
        int a;
        List<Integer> list =new ArrayList<>();
        commendMapper.getCommendList(uid).forEach((Commend i) -> list.add(i.getUid()));
        return list;
    }
}
