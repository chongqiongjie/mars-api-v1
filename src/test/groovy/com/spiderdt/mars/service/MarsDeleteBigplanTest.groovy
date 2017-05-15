package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/5/15.
 */
class MarsDeleteBigplanTest extends BaseTest{
    @Autowired
    MarsDeleteBigplanService marsDeleteBigplanService

    @Test
    void delete(){
        def name = "ffff"
        marsDeleteBigplanService.delete(name)
    }
}
