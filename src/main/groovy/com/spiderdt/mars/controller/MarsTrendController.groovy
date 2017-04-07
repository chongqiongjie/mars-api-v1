package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsTrendService
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
 * Created by chong on 2017/3/20.
 */
@Controller
class MarsTrendController {
    @Autowired
    MarsTrendService marsTrendService

    @Autowired
    JSONObject response

    @RequestMapping(value = "/trend/category", method = RequestMethod.GET)
    @ResponseBody
    def getCategory(){
         def data = marsTrendService.getCategory()
         response.put("status", "success")
         response.put("data", data)
         return ResponseEntity.status(HttpStatus.OK).body(response.toString())
    }

    @RequestMapping(value = "/trend/trend_spline", method = RequestMethod.POST)
    @ResponseBody
    def getScore(@RequestBody Map<String, String> params){
        def category_1 = params.get("category_1");
        def category_2 = params.get("category_2");
        def product_name = params.get("product_name");
        println("1:" + category_1)
        println("2:" + category_2)
        println("3:" + product_name)

        if(category_1 ==null&category_2==null&product_name==null){
            def data =   marsTrendService.getAllScore()
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1!=null&category_2 != null&product_name != null){
            def data =   marsTrendService.getProductScore(category_1,category_2,product_name)
           // println(data)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        } else if(category_1!=null&category_2 != null&product_name==null){
            def data =  marsTrendService.getCategory2Score(category_1,category_2)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 != null&category_2 == null){
            def data =  marsTrendService.getCategory1Score(category_1)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }
    }

}
