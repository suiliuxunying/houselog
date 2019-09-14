package com.shao.house.houselog.mapper;

import com.shao.house.houselog.model.Rating;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingMapperExtend {
    List<Rating> getRatingList();
}
