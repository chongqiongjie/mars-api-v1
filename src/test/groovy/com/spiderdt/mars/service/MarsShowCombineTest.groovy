package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/5/12.
 */
class MarsShowCombineTest extends BaseTest {
    @Autowired
    MarsShowCombineService marsShowCombineService

    @Test
    void show(){
        marsShowCombineService.show()
    }
}
