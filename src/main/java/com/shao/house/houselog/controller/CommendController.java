package com.shao.house.houselog.controller;

import com.shao.house.houselog.model.Commend;
import com.shao.house.houselog.service.CommendService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class CommendController {

    @Autowired
    CommendService commendService;

    @RequestMapping(value = {"/deleteAll"})
    public void deleteAll(){
        commendService.deleteAll();
    }
    @RequestMapping(value = "/getCommendList")
    public List<Integer> getCommendList(@RequestBody JSONObject object) throws JSONException {
        Integer uid =Integer.valueOf(object.getInt("uid"));
        return  commendService.getCommendList(uid);
    }
}
