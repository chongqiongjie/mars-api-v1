package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsTbService
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
 * Created by chong on 2017/3/28.
 */
@Controller
class MarsTbController {
    @Autowired
    MarsTbService marsTbService

    @RequestMapping(value = "/trend/product_tb", method = RequestMethod.GET)
    @ResponseBody
    def getTbScore(HttpServletRequest request){
        JSONObject response = new JSONObject()
        def category_1 = request.getParameter("category_1");
        def category_2 = request.getParameter("category_2");
        if(category_1!=null&category_2 != null){
            def data = marsTbService.getProductTb(category_1,category_2)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 != null&category_2==null){
            def data = marsTbService.getCategory2Tb(category_1)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1==null&category_2==null){
            def data = marsTbService.getCategory1Tb()
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }
    }

}
