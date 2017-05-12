package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/5/12.
 */
class MarsDeleteSubplanTest  extends  BaseTest{
    @Autowired
    MarsDeleteSubplanService marsDeleteSubplanService

    @Test
    void delete(){
        def name = "计划1"
        marsDeleteSubplanService.delete(name)
    }
}
