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
        def week = request.getParameter("week")
        def month = request.getParameter("month")

        if(category_1 != null&category_2 != null) {
            def top10_week_quantity = marsQuantityService.GetProductTop10WeekQuantity(category_1,category_2,week)
            def bottom10_week_quantity = marsQuantityService.GetProductBottom10WeekQuantity(category_1,category_2,week)
            def other_week_quantity = marsQuantityService.GetProductOtherWeekQuantity(category_1,category_2,week)
            def top10_month_quantity = marsQuantityService.GetProductTop10MonthQuantity(category_1,category_2,week)
            def bottom10_month_quantity = marsQuantityService.GetProductBottom10MonthQuantity(category_1,category_2,week)
            def other_month_quantity = marsQuantityService.GetProductOtherMonthQuantity(category_1,category_2,week)

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
            def top10_week_quantity = marsQuantityService.GetCategory2Top10WeekQuantity(category_1,week)
            def bottom10_week_quantity = marsQuantityService.GetCategory2Bottom10WeekQuantity(category_1,week)
            def other_week_quantity = marsQuantityService.GetCategory2OtherWeekQuantity(category_1,week)
            def top10_month_quantity = marsQuantityService.GetCategory2Top10MonthQuantity(category_1,week)
            def bottom10_month_quantity = marsQuantityService.GetCategory2Bottom10MonthQuantity(category_1,week)
            def other_month_quantity = marsQuantityService.GetCategory2OtherMonthQuantity(category_1,week)


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
            def top10_week_quantity = marsQuantityService.GetCategory1Top10WeekQuantity(week)
            def bottom10_week_quantity = marsQuantityService.GetCategory1Bottom10WeekQuantity(week)
            def other_week_quantity = marsQuantityService.GetCategory1OtherWeekQuantity(week)
            def top10_month_quantity = marsQuantityService.GetCategory1Top10MonthQuantity(week)
            def bottom10_month_quantity = marsQuantityService.GetCategory1Bottom10MonthQuantity(week)
            def other_month_quantity = marsQuantityService.GetCategory1OtherMonthQuantity(week)

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
