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
        //marsTrendService.buildCategory();

    }


//
//    @Test
//    void getScore(){
//        def category_1 = '底妆彩妆'
//        def category_2 = '底妆'
//        def product_name = 'CANMAKE Be My Baby 雪嫩柔滑婴儿肌多效BB霜 SPF50+ PA ++++ 20g'
//        if(category_1 ==null&category_2==null&product_name==null){
//               marsTrendService.getAllScore()
//        }else if(product_name != null){
//              marsTrendService.getProductScore(category_1,category_2,product_name)
//
//        } else if(category_2 != null){
//             marsTrendService.getCategory2Score(category_1,category_2)
//
//        }else{
//             marsTrendService.getCategory1Score(category_1)
//
//        }

        @Test
        void getAllScore(){
            marsTrendService.getAllScore()
        }

        @Test
        void getProductScore(){
            def category_1 = '底妆彩妆'
            def category_2 = '底妆'
            def product_name = 'CANMAKE Be My Baby 雪嫩柔滑婴儿肌多效BB霜 SPF50+ PA ++++ 20g'
            marsTrendService.getProductScore(category_1,category_2,product_name)
        }

        @Test
        void getCategory2Score(){
            def category_1 = '底妆彩妆'
            def category_2 = '底妆'
            marsTrendService.getCategory2Score(category_1,category_2)
        }

        @Test
        void getCategory1Score(){
            def category_1 = '底妆彩妆'
            marsTrendService.getCategory1Score(category_1)
        }

    }

