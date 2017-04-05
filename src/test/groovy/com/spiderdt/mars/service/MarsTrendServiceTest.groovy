package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/3/17.
 */
class MarsTrendServiceTest extends BaseTest {
    @Autowired
    MarsTrendService marsTrendService

    @Test
    void testGetCategory() {
        marsTrendService.getCategory();
    }



    @Test
    void getScore(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
        def product_name = "ORBIS奥蜜思 钙镁片 黄金骨骼2:1双料同补 特惠装 450粒"
        if(category_1 ==null&category_2==null&product_name==null){
               marsTrendService.getAllScore()
        }else if(product_name != null){
              marsTrendService.getProductScore(category_1,category_2,product_name)

        } else if(category_2 != null){
             marsTrendService.getCategory2Score(category_1,category_2)

        }else{
             marsTrendService.getCategory1Score(category_1)

        }


    }
}
