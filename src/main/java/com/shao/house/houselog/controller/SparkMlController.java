package com.shao.house.houselog.controller;

import com.shao.house.houselog.model.Commend;
import com.shao.house.houselog.service.SparkMlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class SparkMlController {
    @Autowired
    private SparkMlService sparkMlService;
//http://localhost:9092/recommend
    @RequestMapping(value = {"/dataProcess"})
    public void dataProcess(){
        sparkMlService.dataProcess();
    }

    @RequestMapping(value = {"/recommend"})
    public List<Commend> recommend(){
        return  sparkMlService.getreCommend();
    }
}
