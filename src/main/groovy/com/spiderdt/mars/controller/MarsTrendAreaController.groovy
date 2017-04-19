package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsTrendAreaService
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
 * Created by chong on 2017/4/12.
 */
@Controller
class MarsTrendAreaController {
    @Autowired
    MarsTrendAreaService marsTrendAreaService


    @RequestMapping(value = "/createplan/trendarea ", method = RequestMethod.GET)
    @ResponseBody
    def getTrendAreaScore(HttpServletRequest request){
        JSONObject response = new JSONObject()
        def data = new HashMap()
        def category_1 = request.getParameter("category_1")
        def category_2 = request.getParameter("category_2")
        def product_name = request.getParameter("product_name")
        def start_time = request.getParameter("start_time")
        def end_time = request.getParameter("end_time")



        if(category_1 ==null&category_2==null&product_name==null){
            def area_cat =   marsTrendAreaService.getCategory1Score(start_time,end_time)
            def avg_cat = marsTrendAreaService.getCategory1MaxmonthScore()

            data.put("area_cat",area_cat)
            data.put("avg_cat",avg_cat)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 !=null&category_2==null&product_name==null){
            def area_cat = marsTrendAreaService.getCategory2Score(category_1,start_time,end_time)
            def avg_cat = marsTrendAreaService.getCategory2MaxmonthScore(category_1)

            data.put("area_cat",area_cat)
            data.put("avg_cat",avg_cat)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 !=null&category_2 !=null&product_name==null){
            def area_cat  = marsTrendAreaService.getProductScore(category_1,category_1,start_time,end_time)
            def avg_cat = marsTrendAreaService.getProductMaxmonthScore(category_1,category_2)

            data.put("area_cat",area_cat)
            data.put("avg_cat",avg_cat)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 !=null&category_2 !=null&product_name !=null){
            def area_cat = marsTrendAreaService.getSingleProductScore(category_1,category_2,product_name,start_time,end_time)
            def avg_cat = marsTrendAreaService.getSingleProductMaxmonthScore(category_1,category_2,product_name)

            data.put("area_cat",area_cat)
            data.put("avg_cat",avg_cat)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }
    }
}
