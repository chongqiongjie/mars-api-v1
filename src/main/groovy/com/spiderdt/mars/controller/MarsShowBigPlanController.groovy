package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsShowBigPlanService
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
 * Created by chong on 2017/5/12.
 */
@Controller
class MarsShowBigPlanController {
    @Autowired
    MarsShowBigPlanService marsShowBigPlanService

    @RequestMapping(value = "/plans/showbigplans", method = RequestMethod.GET)
    @ResponseBody
    def show(HttpServletRequest request){
        JSONObject response = new JSONObject()
        def name = request.getParameter("name")

        def data = marsShowBigPlanService.show(name)

        response.put("data",data)
        response.put("status","success")
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())
    }
}
