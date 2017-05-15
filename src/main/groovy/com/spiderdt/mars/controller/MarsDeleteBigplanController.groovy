package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsDeleteBigplanService
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
 * Created by chong on 2017/5/15.
 */
@Controller
class MarsDeleteBigplanController {
    @Autowired
    MarsDeleteBigplanService marsDeleteBigplanService

    @RequestMapping(value = "/plans/deletebigplan", method = RequestMethod.GET)
    @ResponseBody
    def delete(HttpServletRequest request){
        JSONObject response = new JSONObject()
        def name = request.getParameter("name")

        marsDeleteBigplanService.delete(name)
        response.put("status","success")
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())
    }
}
