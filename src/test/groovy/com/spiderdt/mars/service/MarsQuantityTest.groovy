package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/4/5.
 */
class MarsQuantityTest extends BaseTest {

    @Autowired
    MarsQuantityService marsQuantityService

    @Test
    def getCategory1Top10WeekTest(){
        def week = "week"
        marsQuantityService.GetCategory1Top10WeekQuantity(week)
    }

    def getCategory2Top10WeekTest(){
        def category_1 = "保健养生"
        def week = "week"
    }

}
