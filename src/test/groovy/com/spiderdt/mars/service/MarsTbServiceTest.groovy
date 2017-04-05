package com.spiderdt.mars.service

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by chong on 2017/3/24.
 */
class MarsTbServiceTest extends  BaseTest{
    @Autowired
    MarsTbService marsTbService

    @Test
    void MarsProductTbTest(){
        def category_1 = "保健养生"
        def category_2 = "美容养生"
       // def product_name = "ORBIS奥蜜思 钙镁片 黄金骨骼2:1双料同补 特惠装 450粒"
//        if(category_1 != null&category_2 != null){
//            marsTbService.getProductTb(category_1,category_2)
//        }else if(category_1 != null&category_2==null){
//            marsTbService.getCategory2Tb(category_1)
//        }else if(category_1==null&category_2==null){
//            marsTbService.getCategory1Tb()
//        }
        marsTbService.getProductTb(category_1,category_2)
    }

    @Test
    void MarsCategory2TbTest(){
        def category_1 = "保健养生"
        marsTbService.getCategory2Tb(category_1)
    }

    @Test
    void MarsCategory1TbTest(){
        marsTbService.getCategory1Tb()
    }
}
