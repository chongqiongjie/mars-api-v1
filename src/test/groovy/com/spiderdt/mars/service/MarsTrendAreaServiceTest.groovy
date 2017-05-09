package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/4/13.
 */
class MarsTrendAreaServiceTest extends BaseTest {

    @Autowired
    MarsTrendAreaService marsTrendAreaService

    @Test
    void getCategory1Test(){
        def start_time = "2016-04-10"
        def end_time = "2016-04-12"
        marsTrendAreaService.getCategory1Score(start_time,end_time)
    }

    @Test
    void getCategory2Test(){
        def category_1 = "1.0000000000000000"
        def start_time = "2016-04-14"
        def end_time = "2016-04-20"
        marsTrendAreaService.getCategory2Score(category_1,start_time,end_time)
    }

    @Test
    void getProductTest(){
        def category_1 = "1.0000000000000000"
        def category_2 = "1.0000000000000000"
        def start_time = "2016-04-14"
        def end_time = "2016-04-20"
        marsTrendAreaService.getProductScore(category_1,category_2,start_time,end_time)
    }

    @Test
    void getSingleProductTest(){
        def category_1 = "1.0000000000000000"
        def category_2 = "1.0000000000000000"
        def product_name = "春夏家居服礼包"
        def start_time = "2016-04-14"
        def end_time = "2016-04-20"
        marsTrendAreaService.getSingleProductScore(category_1,category_2,product_name,start_time,end_time)
    }







    @Test
    void getCategory1MaxmonthTest(){
        marsTrendAreaService.getCategory1MaxmonthScore()
    }

    @Test
    void getCategory2MaxmonthTest(){
        def cat1_id = "1"
        marsTrendAreaService.getCategory2MaxmonthScore(cat1_id)
    }


    @Test
    void getProductMaxmonthTest(){
        def cat1_id = "1"
        def cat2_id = "1"
        marsTrendAreaService.getProductMaxmonthScore(cat1_id,cat2_id)
    }

    @Test
    void getSingleProductMaxmonthTest(){
        def cat1_id = "1"
        def cat2_id= "1"
        def ppd_id = "102"
        marsTrendAreaService.getSingleProductMaxmonthScore(cat1_id,cat2_id,ppd_id)
    }
}
