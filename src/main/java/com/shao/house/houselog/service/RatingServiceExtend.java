package com.shao.house.houselog.service;

import com.shao.house.houselog.mapper.RatingMapper;
import com.shao.house.houselog.mapper.RatingMapperExtend;
import com.shao.house.houselog.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RatingServiceExtend {
    @Autowired
    private RatingMapperExtend ratingMapperExtend;
    @Autowired
    private RatingMapper ratingMapper;


    public List<Rating> getRatingList() {
        return ratingMapperExtend.getRatingList();
    }

    /**
     * 模拟用户对房产的评分！
     * 收藏：  5分
     * 没看过： 0分
     * 看过没收藏(限制条件)：0-5分
     * 用户对 50%--5%的房产打分（看过）
     * 比例：1 70% 2 30%  3 10%  4 10%  5  10%
     *
     * @return
     */
    public List<Rating> writeData() {
        List<Rating> list = new ArrayList<>();

//        userid 103-203
        for (int j0 = 103; j0 <= 203; j0++) {
//        48-1036房屋id
            for (int j1 = 48; j1 <= 103; j1++) {
                int a1 = (int) (1 + Math.random() * (100 - 1 + 1));
                //用户对 50%--5%的房产打分（看过）
                if (a1 > 0 && a1 < 5) {
                    Rating rating = new Rating();
                    rating.setHid(j1);
                    rating.setUid(j0);
                    double a2;
                    do {
                        a2 = Math.sqrt(5) * new Random().nextGaussian();
                    } while (Math.abs(a2) < 0 || Math.abs(a2) > 5);
                    rating.setRating(Math.abs(a2));
                    //ratingMapper.insertSelective(rating);
                    list.add(rating);
                } else {
//                    没看过
                }

            }
        }
        return list;

    }
}
