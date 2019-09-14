package com.shao.house.houselog.mapper;

import com.shao.house.houselog.model.Rating;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rating record);

    int insertSelective(Rating record);

    Rating selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rating record);

    int updateByPrimaryKey(Rating record);

}