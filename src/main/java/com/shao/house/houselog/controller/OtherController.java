package com.shao.house.houselog.controller;

import com.shao.house.houselog.model.Rating;
import com.shao.house.houselog.service.RatingService;
import com.shao.house.houselog.service.RatingServiceExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin//解决跨域问题！
@Controller
@ResponseBody
public class OtherController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private RatingServiceExtend ratingServiceExtend;

    @RequestMapping(value = {"/writeData"})
    public List<Rating> writeData (){

        return ratingServiceExtend.writeData();
    }
}
