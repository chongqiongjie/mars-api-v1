package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsShowCombineService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by chong on 2017/5/11.
 */
@Controller
class MarsShowCombineController {
    @Autowired
    MarsShowCombineService marsShowCombineService

    @RequestMapping(value = "/plans/showallplans", method = RequestMethod.GET)
    @ResponseBody
    def show(){
        JSONObject response = new JSONObject()
        def res =  marsShowCombineService.show()

        response.put("data",res)
        response.put("status","success")
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())
    }
}
