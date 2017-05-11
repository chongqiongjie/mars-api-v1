package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsShowSubplanService
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
 * Created by chong on 2017/5/8.
 */
@Controller
class MarsShowSubplanController {
    @Autowired
    MarsShowSubplanService marsShowSubplanService

    @RequestMapping(value = "/createplan/showsubplan", method = RequestMethod.GET)
    @ResponseBody
    def showSubplan(HttpServletRequest request){
        JSONObject response = new JSONObject()
        String name = request.getParameter("name")
        def result =  marsShowSubplanService.show(name)

        response.put("status","success")
        response.put("result",result)
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())
    }
}
