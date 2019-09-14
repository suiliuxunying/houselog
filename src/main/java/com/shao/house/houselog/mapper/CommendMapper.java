package com.shao.house.houselog.mapper;

import com.shao.house.houselog.model.Commend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Commend record);

    int insertSelective(Commend record);

    Commend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Commend record);

    int updateByPrimaryKey(Commend record);

    void deleteAll();

    List<Commend> getCommendList(Integer uid);
}