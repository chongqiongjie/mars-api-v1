package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsCombinePlanService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletRequest

/**
 * Created by chong on 2017/5/9.
 */
@Controller
class MarsCombinePlanController {
    @Autowired
    MarsCombinePlanService marsCombinePlanService

    @RequestMapping(value = "/createplan/combinesubplan", method = RequestMethod.POST)
    @ResponseBody
    def Combine(@RequestBody Map<String,Object> params){
        JSONObject response = new JSONObject()
        def name = params.get("name")
        def start_time = params.get("begin_time")
        def end_time = params.get("end_time")
        List subplans = params.get("subplans")
        println("subplans:" + subplans)
        def result = marsCombinePlanService.combine(name,subplans,start_time,end_time)

        response.put("status",result)
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())
    }
}
