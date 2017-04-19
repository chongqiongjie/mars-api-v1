package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.temporal.WeekFields

/**
 * Created by chong on 2017/4/5.
 */
class MarsQuantityTest extends BaseTest {

    @Autowired
    MarsQuantityService marsQuantityService

    @Test
    void getCategory1Top10WeekTest(){
        def week = "week"
        marsQuantityService.getCategory1Top10WeekQuantity(week)
       
    }

    @Test
    void getCategory2Top10WeekTest(){
        def category_1 = "保健养生"
        def week = "week"
        marsQuantityService.getCategory2Top10WeekQuantity(category_1,week)
    }

    @Test
    void getProductTop10WeekTest(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
        def week = "week"
        marsQuantityService.getProductTop10WeekQuantity(category_1,category_2,week)
    }

    @Test
    void getCategory1Bottom10Test(){
        def week = "week"
        marsQuantityService.getCategory1Bottom10WeekQuantity(week)
    }

    @Test
    void getCategory2Bottom10Test(){
        def week = "week"
        def category_1 = "保健养生"
        marsQuantityService.getCategory2Bottom10WeekQuantity(category_1,week)
    }

    @Test
    void getProductBottom10Test(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
        def week = "week"
        marsQuantityService.getProductBottom10WeekQuantity(category_1,category_2,week)
    }

    @Test
    void getCategory1OtherTest(){
        def week = "week"
        marsQuantityService.getCategory1OtherWeekQuantity(week)
    }

    @Test
    void getCategory2OtherTest(){
        def week = "week"
        def category_1 = "保健养生"
        marsQuantityService.getCategory2OtherWeekQuantity(category_1,week)
    }

    @Test
    void getProductOtherTest(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
        def week = "week"
        marsQuantityService.getProductOtherWeekQuantity(category_1,category_2,week)
    }

}
