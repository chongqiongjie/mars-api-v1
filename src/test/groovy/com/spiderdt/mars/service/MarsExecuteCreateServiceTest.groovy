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
        def name = "_chong"
        def category = "所有品类"
        def group = "所有商品组"
        def product = "所有商品"
        def start_time = "2016-12-13"
        def end_time = "2017-01-15"
        def drivers = [effect_coupon:"1",price:"1",effect_ln_baseprice:"1",effect_debut:"1",effect_discount:"1"]
        marsExecuteCreateService.create(url,name,category,group,product,start_time,end_time,drivers)
    }
}
