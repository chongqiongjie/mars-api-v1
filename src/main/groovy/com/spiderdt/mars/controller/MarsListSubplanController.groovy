package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsListSubplanService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletRequest

/**
 * Created by chong on 2017/4/13.
 */
@Controller
class MarsListSubplanController {
    @Autowired
    MarsListSubplanService marsListSubplanService

    @RequestMapping(value = "/createplan/subplan",method = RequestMethod.GET)
    @ResponseBody
    def listSubplan(){
        JSONObject response = new JSONObject()
        def data = marsListSubplanService.ListSubplan()

        response.put("status", "success")
        response.put("data", data)
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())

    }
}
