package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/4/13.
 */
class MarsCreateSubplanServiceTest extends BaseTest{
    @Autowired
    MarsCreateSubplanService marsCreateSubplanService

    @Test
    void createSubplanTest(){
        def name = "q"
        def category_1 = "生活用品"
        def category_2 = "厨房用品"
        def product_name = "铲子"
        def start_time = "2016-02-02"
        def end_time = "2016-02-09"
        def price = 20
        def discount = 8
        def coupon = 10
        def effect_ln_baseprice = 8
        def debut = 2





//        def name = "qq"
//        def category_1 = "生活用品"
//        def category_2 = "厨房用品"
//        def product_name = "锅"
//        def start_time = "2016-02-22"
//        def end_time = "2016-02-25"
//        def price = "19"
//        def discount = "2"
//        def coupon = "3"
//        def display = "4"
//        def promote = "4"
//        def debut = "44"
//        def is_collected = "0"
//        def status = "success"
        marsCreateSubplanService.createSubplan(name,category_1,category_2,product_name,start_time,end_time,price,discount,coupon,effect_ln_baseprice,debut)
    }


}
