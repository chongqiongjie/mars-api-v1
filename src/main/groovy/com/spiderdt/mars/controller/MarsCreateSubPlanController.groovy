package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsCreateSubplanService
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

    @RequestMapping(value = "/createplan/subplan", method = RequestMethod.POST)
    @ResponseBody
    def CreateSubplan(@RequestBody Map<String, Object> params){
        JSONObject response = new JSONObject()
        String name = params.get("name")
        String category_1 = params.get("category")
        String category_2 = params.get("group")
        String product_name = params.get("product")
        String start_time = params.get("start_time")
        String end_time = params.get("end_time")
        String price = params.get("drivers").get("price")
        String discount = params.get("drivers").get("effect_discount")
        String coupon = params.get("drivers").get("effect_coupon")
        String effect_ln_baseprice = params.get("drivers").get("effect_ln_baseprice")
        String debut = params.get("drivers").get("effect_debut")



        marsCreateSubplanService.createSubplan(name,category_1,category_2,product_name,start_time,end_time,price,discount,coupon,effect_ln_baseprice,debut)

        response.put("status", "success")
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())

    }
}
