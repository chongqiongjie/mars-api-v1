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

    @RequestMapping(value = "/trend/product_hb", method = RequestMethod.GET)
    @ResponseBody
    def  getHbScore(HttpServletRequest request){
        JSONObject response = new JSONObject()
        def data = new HashMap()
        def category_1 = request.getParameter("category_1")
        def category_2 = request.getParameter("category_2")
        def product_name = request.getParameter("product_name")
        def week = request.getParameter("week")
        def month = request.getParameter("month")
        if(category_1 != null&category_2 != null&product_name != null){
            def top10_week_hb = marsHbService.getSingleProductWeekHb(category_1,category_2,product_name,week)
            def bottom10_week_hb = marsHbService.getSingleProductWeekHb(category_1,category_2,product_name,week)
            def top10_month_hb = marsHbService.getSingleProductMonthHb(category_1,category_2,product_name,week)
            def bottom_10_month_hb = marsHbService.getSingleProductMonthHb(category_1,category_2,product_name,week)

            data.put("top10_week_hb",top10_week_hb)
            data.put("bottom10_week_hb",bottom10_week_hb)
            data.put("top10_month_hb",top10_month_hb)
            data.put("bottom_10_month_hb",bottom_10_month_hb)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        } else if(category_1 != null&category_2 != null){
            def top10_week_hb =  marsHbService.getProductTop10WeekHb(category_1,category_2,week)
            def bottom10_week_hb = marsHbService.getProductBottom10WeekHb(category_1,category_2,week)
            def top10_month_hb = marsHbService.getProductTop10MonthHb(category_1,category_2,month)
            def bottom_month_hb = marsHbService.getProductBottom10MonthHb(category_1,category_2,month)

            data.put("top10_week_hb",top10_week_hb )
            data.put("bottom10_week_hb",bottom10_week_hb)
            data.put("top10_month_hb",top10_month_hb)
            data.put("bottom10_month_hb",bottom_month_hb)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 != null&category_2==null){
            def top10_week_hb =  marsHbService.getCategory2Top10WeekHb(category_1,week)
            def bottom10_week_hb =  marsHbService.getCategory2Bottom10WeekHb(category_1,week)
            def top10_month_hb = marsHbService.getCategory2Top10MonthHb(category_1,month)
            def bottom10_month_hb = marsHbService.getCategory2Bottom10MonthHb(category_1,month)
            data.put("top10_week_hb",top10_week_hb)
            data.put("bottom10_week_hb",bottom10_week_hb)
            data.put("top10_month_hb",top10_month_hb)
            data.put("bottom10_month_hb",bottom10_month_hb)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1==null&category_2==null){
            def top10_week_hb = marsHbService.getCategory1Top10WeekHb(month)
            def bottom10_week_hb = marsHbService.getCategory1Bottom10WeekHb(month)
            def top10_month_hb = marsHbService.getCategory1Top10MonthHb(month)
            def bottom10_month_hb = marsHbService.getCategory1Bottom10MonthHb(month)

            data.put("top10_week_hb",top10_week_hb)
            data.put("bottom10_week_hb",bottom10_week_hb)
            data.put("top10_month_hb",top10_month_hb)
            data.put("bottom10_month_hb",bottom10_month_hb)
            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }
    }





}
