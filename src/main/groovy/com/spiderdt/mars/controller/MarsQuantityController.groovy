package com.spiderdt.mars.controller

import com.spiderdt.mars.service.MarsQuantityService
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
 * Created by chong on 2017/4/6.
 */
@Controller
class MarsQuantityController {
    @Autowired
    MarsQuantityService marsQuantityService

    @Autowired
    JSONObject response

    @RequestMapping(value = "/trend/quantity", method = RequestMethod.GET)
    @ResponseBody

    def getQuantityScore(HttpServletRequest request){
        def data = new HashMap()
        def category_1 = request.getParameter("category_1")
        def category_2 = request.getParameter("category_2")
        def product_name = request.getParameter("product_name")
        def week = request.getParameter("week")
        def month = request.getParameter("month")

        if(category_1 != null&category_2 != null&product_name != null){
            def top10_week_quantity = marsQuantityService.getSingleProductTop10WeekQuantity(category_1,category_2,product_name,week)
            def bottom10_week_quantity = marsQuantityService.getSingleProductBottom10WeekQuantity(category_1,category_2,product_name,week)
            def top10_month_quantity = marsQuantityService.getSingleProductTop10MonthQuantity(category_1,category_2,product_name,month)
            def bottom10_month_quantity = marsQuantityService.getSingleProductBottom10MonthQuantity(category_1,category_2,product_name,month)

            data.put("top10_week_quantity",top10_week_quantity)
            data.put("bottom10_week_quantity",bottom10_week_quantity)
            data.put("top10_month_quantity",top10_month_quantity)
            data.put("bottom10_month_quantity",bottom10_month_quantity)

            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        } else if(category_1 != null&category_2 != null) {
            def top10_week_quantity = marsQuantityService.getProductTop10WeekQuantity(category_1,category_2,week)
            def bottom10_week_quantity = marsQuantityService.getProductBottom10WeekQuantity(category_1,category_2,week)
            def other_week_quantity = marsQuantityService.getProductOtherWeekQuantity(category_1,category_2,week)
            def top10_month_quantity = marsQuantityService.getProductTop10MonthQuantity(category_1,category_2,month)
            def bottom10_month_quantity = marsQuantityService.getProductBottom10MonthQuantity(category_1,category_2,month)
            def other_month_quantity = marsQuantityService.getProductOtherMonthQuantity(category_1,category_2,month)

            data.put("top10_week_quantity",top10_week_quantity)
            data.put("bottom10_week_quantity",bottom10_week_quantity)
            data.put("other_week_quantity",other_week_quantity)
            data.put("top10_month_quantity",top10_month_quantity)
            data.put("bottom10_month_quantity",bottom10_month_quantity)
            data.put("other_month_quantity",other_month_quantity)

            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 != null&category_2 ==null){
            def top10_week_quantity = marsQuantityService.getCategory2Top10WeekQuantity(category_1,week)
            def bottom10_week_quantity = marsQuantityService.getCategory2Bottom10WeekQuantity(category_1,week)
            def other_week_quantity = marsQuantityService.getCategory2OtherWeekQuantity(category_1,week)
            def top10_month_quantity = marsQuantityService.getCategory2Top10MonthQuantity(category_1,month)
            def bottom10_month_quantity = marsQuantityService.getCategory2Bottom10MonthQuantity(category_1,month)
            def other_month_quantity = marsQuantityService.getCategory2OtherMonthQuantity(category_1,month)


            data.put("top10_week_quantity",top10_week_quantity)
            data.put("bottom10_week_quantity",bottom10_week_quantity)
            data.put("other_week_quantity",other_week_quantity)
            data.put("top10_month_quantity",top10_month_quantity)
            data.put("bottom10_month_quantity",bottom10_month_quantity)
            data.put("other_month_quantity",other_month_quantity)

            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }else if(category_1 == null&category_2 == null){
            def top10_week_quantity = marsQuantityService.getCategory1Top10WeekQuantity(week)
            def bottom10_week_quantity = marsQuantityService.getCategory1Bottom10WeekQuantity(week)
            def other_week_quantity = marsQuantityService.getCategory1OtherWeekQuantity(week)
            def top10_month_quantity = marsQuantityService.getCategory1Top10MonthQuantity(month)
            def bottom10_month_quantity = marsQuantityService.getCategory1Bottom10MonthQuantity(month)
            def other_month_quantity = marsQuantityService.getCategory1OtherMonthQuantity(month)

            data.put("top10_week_quantity",top10_week_quantity)
            data.put("bottom10_week_quantity",bottom10_week_quantity)
            data.put("other_week_quantity",other_week_quantity)
            data.put("top10_month_quantity",top10_month_quantity)
            data.put("bottom10_month_quantity",bottom10_month_quantity)
            data.put("other_month_quantity",other_month_quantity)

            response.put("status", "success")
            response.put("data", data)
            return ResponseEntity.status(HttpStatus.OK).body(response.toString())
        }
    }
}
