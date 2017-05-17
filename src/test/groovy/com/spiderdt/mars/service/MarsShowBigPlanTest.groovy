package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/5/11.
 */
class MarsShowBigPlanTest extends  BaseTest{
    @Autowired
    MarsShowBigPlanService marsShowBigPlanService

    @Test
    void show(){
        def name = "chong"
        marsShowBigPlanService.show(name)
    }
}
