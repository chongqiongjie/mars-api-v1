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
        def list = ['123','1']
        marsCombinePlanService.combine(list)
    }
}
