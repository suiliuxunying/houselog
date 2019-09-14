package com.shao.house.houselog.service;

import com.shao.house.houselog.mapper.RatingMapper;
import com.shao.house.houselog.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    @Autowired
    private RatingMapper ratingMapper;

    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }


    public int insert(Rating record) {
        return 0;
    }

    public int insertSelective(Rating record) {
        return ratingMapper.insertSelective(record);
    }

    public Rating selectByPrimaryKey(Integer id) {
        return ratingMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Rating record) {
        return 0;
    }

    public int updateByPrimaryKey(Rating record) {
        return 0;
    }


}
