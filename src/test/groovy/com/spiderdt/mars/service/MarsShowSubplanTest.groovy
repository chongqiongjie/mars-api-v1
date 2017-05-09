package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/5/8.
 */
class MarsShowSubplanTest extends BaseTest{
    @Autowired
    MarsShowSubplanService marsShowSubplanService

    @Test
    void show(){
        def name = '123'
        marsShowSubplanService.show(name)
    }

}
