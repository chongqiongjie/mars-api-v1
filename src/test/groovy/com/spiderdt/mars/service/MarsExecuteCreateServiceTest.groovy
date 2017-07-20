package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/4/28.
 */
class MarsExecuteCreateServiceTest extends BaseTest{
    @Autowired
    MarsExecuteCreateService marsExecuteCreateService

    @Test
    void execute(){
        def url = "/predict/sub_promo_plan"
        def name = "qqqq"
        def category = "1"
        def group = "1"
        def product = 1
        def start_time = "2016-12-13"
        def end_time = "2017-01-15"
        def drivers = [coupon:1,price:1,ln_baseprice:1,debut:1,discount:1]
        marsExecuteCreateService.create(url,name,category,group,product,start_time,end_time,drivers)
    }


    }

