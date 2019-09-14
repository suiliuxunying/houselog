package com.shao.house.houselog.controller;

import com.shao.house.houselog.model.Rating;
import com.shao.house.houselog.service.RatingServiceExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@CrossOrigin//解决跨域问题！
@Controller
@ResponseBody
public class RatingController {
    @Autowired
    private RatingServiceExtend ratingServiceExtend;

    @RequestMapping(value = {"/getRatingList"})
    public List<Rating> getRatingList () {
        return ratingServiceExtend.getRatingList();
    }
}
