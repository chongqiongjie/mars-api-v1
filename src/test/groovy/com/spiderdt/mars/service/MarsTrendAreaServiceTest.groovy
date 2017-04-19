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
        def category_1 = "1.0000000000000000"
        marsTrendAreaService.getCategory2MaxmonthScore(category_1)
    }


    @Test
    void getProductMaxmonthTest(){
        def category_1 = "1.0000000000000000"
        def category_2 = "1.0000000000000000"
        marsTrendAreaService.getProductMaxmonthScore(category_1,category_2)
    }

    @Test
    void getSingleProductMaxmonthTest(){
        def category_1 = "1.0000000000000000"
        def category_2 = "1.0000000000000000"
        def product_name = "tutuanna短袜 女士夏季新款纯色花边女袜 日系 清新 舒适 棉袜"
        marsTrendAreaService.getSingleProductMaxmonthScore(category_1,category_2,product_name)
    }
}
