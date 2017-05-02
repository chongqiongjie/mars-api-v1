package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsCreateSubplanService
import com.spiderdt.mars.service.MarsExecuteCreateService
import com.spiderdt.mars.service.MarsTrendAreaService
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
 * Created by chong on 2017/4/13.
 */
@Controller
class MarsCreateSubPlanController {
    @Autowired
    MarsCreateSubplanService marsCreateSubplanService

//    @Autowired
//    MarsExecuteCreateService marsExecuteCreateService


    @RequestMapping(value = "/createplan/subplan", method = RequestMethod.POST)
    @ResponseBody
    def CreateSubplan(@RequestBody Map<String, Object> params){
        JSONObject response = new JSONObject()
        String url = "/predict/sub_promo_plan"
        String name = params.get("name")
        String category = params.get("category")
        String group = params.get("group")
        String product = params.get("product")
        String start_time = params.get("start_time")
        String end_time = params.get("end_time")
        String drivers = params.get("drivers")
        String price = params.get("drivers").get("price")
        String discount = params.get("drivers").get("effect_discount")
        String coupon = params.get("drivers").get("effect_coupon")
        String effect_ln_baseprice = params.get("drivers").get("effect_ln_baseprice")
        String debut = params.get("drivers").get("effect_debut")


       def create =  marsCreateSubplanService.createSubplan(name,category,group,product,start_time,end_time,price,discount,coupon,effect_ln_baseprice,debut)
       //marsExecuteCreateService.create(url,name,category,group,product,start_time,end_time,drivers)

        response.put("create_status",create)
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())

    }
}
