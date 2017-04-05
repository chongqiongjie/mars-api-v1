package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsHbService
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
class MarsHbController {
    @Autowired
    MarsHbService marsHbService

    @Autowired
    JSONObject response

    @RequestMapping(value = "/trend/product_hb", method = RequestMethod.GET)
    @ResponseBody
    def  getHbScore(HttpServletRequest request){
        def data = new HashMap()
        def category_1 = request.getParameter("category_1")
        def category_2 = request.getParameter("category_2")
        def week = request.getParameter("week")
        def month = request.getParameter("month")
        if(category_1 != null&category_2 != null){
            def top5_week_hb =  marsHbService.getProductTop5WeekHb(category_1,category_2,week)
            def bottom5_week_hb = marsHbService.getProductBottom5WeekHb(category_1,category_2,week)
            def top5_month_hb = marsHbService.getProductTop5MonthHb(category_1,category_2,month)
            def bottom_month_hb = marsHbService.getProductBottom5MonthHb(category_1,category_2,month)

            data.put("top5_week_hb",top5_week_hb )
            data.put("bottom5_week_hb",bottom5_week_hb)
            data.put("top5_month_hb",top5_month_hb)
            data.put("bottom_month_hb",bottom_month_hb)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 != null&category_2==null){
            def top5_week_hb =  marsHbService.getCategory2Top5WeekHb(category_1,week)
            def bottom5_week_hb =  marsHbService.getCategory2Bottom5WeekHb(category_1,week)
            def top5_month_hb = marsHbService.getCategory2Top5MonthHb(category_1,month)
            def bottom5_month_hb = marsHbService.getCategory2Bottom5MonthHb(category_1,month)
            data.put("top5_week_hb",top5_week_hb)
            data.put("bottom5_week_hb",bottom5_week_hb)
            data.put("top5_month_hb",top5_month_hb)
            data.put("bottom5_month_hb",bottom5_month_hb)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1==null&category_2==null){
            def top5_week_hb = marsHbService.getCategory1Top5WeekHb(month)
            def bottom5_week_hb = marsHbService.getCategory1Bottom5WeekHb(month)
            def top5_month_hb = marsHbService.getCategory1Top5MonthHb(month)
            def bottom5_month_hb = marsHbService.getCategory1Bottom5MonthHb(month)

            data.put("top5_week_hb",top5_week_hb)
            data.put("bottom5_week_hb",bottom5_week_hb)
            data.put("top5_month_hb",top5_month_hb)
            data.put("bottom5_month_hb",bottom5_month_hb)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }
    }





}
