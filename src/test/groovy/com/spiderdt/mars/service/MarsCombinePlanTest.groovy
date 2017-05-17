package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/5/9.
 */

class MarsCombinePlanTest extends BaseTest{
    @Autowired
    MarsCombinePlanService marsCombinePlanService

    @Test
    void combine(){
        def name  = "jie"
        def list = ["test","test1"]
        def start_time = "2016-12-14"
        def end_time = "2016-12-25"
        marsCombinePlanService.combine(name,list,start_time,end_time)
    }

}
