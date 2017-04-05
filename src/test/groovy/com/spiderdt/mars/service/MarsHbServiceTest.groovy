package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


/**
 * Created by chong on 2017/3/28.
 */
class MarsHbServiceTest extends BaseTest{
    @Autowired
    MarsHbService marsHbService

    @Test
    void getProductTop5HbWeekTest(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
        def week = "week"
        marsHbService.getProductTop5WeekHb(category_1,category_2,week)

    }

    @Test
    void getCategory2Top5WeekTest(){
        def category_1 = "保健养生"
        def week = "week"
        marsHbService.getCategory2Top5WeekHb(category_1,week)
    }


    @Test
    void getCategory1Top5WeekTest(){
        def week = "week"
        marsHbService.getCategory1Top5WeekHb(week)
    }


    @Test
    void getProductBottom5WeekTest(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
        def week = "week"
        marsHbService.getProductBottom5WeekHb(category_1,category_2,week)
    }

    @Test
    void getCategory2Bottom5WeekTest(){
        def category_1 = "保健养生"
        def week = "week"
        marsHbService.getCategory2Bottom5WeekHb(category_1,week)
    }

    @Test
    void getCategory1Bottom5WeekTest(){
        def week = "week"
        marsHbService.getCategory1Bottom5WeekHb(week)
    }





    @Test
    void getProductTop5MonthTest(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
        def month = "month"
        marsHbService.getProductTop5MonthHb(category_1,category_2,month)
    }

    @Test
    void getCategory2Top5MonthTest(){
        def category_1 = "保健养生"
        def month = "month"
        marsHbService.getCategory2Top5MonthHb(category_1,month)
    }

    @Test
    void getCategory1Top5MonthTest(){
        def month = "month"
        marsHbService.getCategory1Top5MonthHb(month)
    }

    @Test
    void getProductBottom5MonthTest(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
        def month = "month"
        marsHbService.getProductBottom5MonthHb(category_1,category_2,month)
    }

    @Test
    void getCategory2Bottom5MonthTest(){
        def category_1 = "保健养生"
        def month = "month"
        marsHbService.getCategory2Bottom5MonthHb(category_1,month)
    }

    @Test
    void getCategory1Bottom5MonthTest(){
        def month = "month"
        marsHbService.getCategory1Bottom5MonthHb(month)
    }
}




