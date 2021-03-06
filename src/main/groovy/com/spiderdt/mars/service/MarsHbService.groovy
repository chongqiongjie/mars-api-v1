package com.spiderdt.mars.service

import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

/**
 * Created by chong on 2017/3/28.
 */
@Service
class MarsHbService  extends psqlService{

//    private final String client_key = new ClassPathResource("psql/client.key.pk8").getURI().getPath()
//    private final String client = new ClassPathResource("psql/client.cert.pem").getURI().getPath()
//    private final String root = new ClassPathResource("psql/root.cert.pem").getURI().getPath()
//
//
//    def sqlClient = openSqlClient("192.168.1.3", "5432", "ms", "spiderdt")
//    def openSqlClient(hostname, port, username, password) {
//        def client3 = new Sql(new PGPoolingDataSource().each {
//            it.url = "jdbc:postgresql://${hostname}:${port}/dw?useSSL=true&ssl=true&characterEncoding=utf-8&stringtype=unspecified&sslmode=require&sslkey=${client_key}&sslcert=${client}&sslrootcert=${root}&sslfactory=org.postgresql.ssl.jdbc4.LibPQFactory".toString()
//            it.user = username
//            it.password = password
//        })
//        [client: client3, args: [hostname:hostname, port:port, username:username, password:password]]
//    }


    def getCategory1Top10WeekHb(String week){
        def hb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select * from stg.d_mars_cat1_week_hb  order by hb desc limit 10");

        hb_1.add(cat_1)
        //println(hb_1)
        return hb_1.get(0)

    }

    def getCategory2Top10WeekHb(String category_1,String week){
        def hb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2, hb  from stg.d_mars_cat2_week_hb where category_1 = ${category_1}  and hb is not null  order by hb desc limit 10 ");

        hb_2.add(cat_2)
        //println(hb_2)
        return hb_2.get(0)

    }

    def getProductTop10WeekHb(String category_1,String category_2,String week){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name, hb from stg.d_mars_product_week_hb where category_1 = ${category_1} and category_2 = ${category_2} and hb is not null  order by hb desc limit 10");

         pro.add(product)
         //println(pro)
         return pro.get(0)
    }


    def getCategory1Bottom10WeekHb(String week){
        def hb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select * from stg.d_mars_cat1_week_hb  order by hb asc limit 10");

        hb_1.add(cat_1)
       // println(hb_1)
        return hb_1.get(0)

    }


    def getCategory2Bottom10WeekHb(String category_1,String week){
        def hb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2, hb  from stg.d_mars_cat2_week_hb where category_1 = ${category_1}  and hb is not null order by hb asc limit 10");

        hb_2.add(cat_2)
        //println(hb_2)
        return hb_2.get(0)

    }

    def getProductBottom10WeekHb(String category_1,String category_2,String week){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name, hb from stg.d_mars_product_week_hb where category_1 = ${category_1} and category_2 = ${category_2} and hb is not null  order by hb asc limit 10");

        pro.add(product)
        //println(pro)
        return pro.get(0)
    }

    def getSingleProductWeekHb(String category_1,String category_2,String product_name,String week){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name, hb from stg.d_mars_product_week_hb where category_1 = ${category_1} and category_2 = ${category_2} and product_name = ${product_name} ");

        pro.add(product)
        //println(pro)
        return pro.get(0)
    }








    def getCategory1Top10MonthHb(String month){
        def hb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select * from stg.d_mars_cat1_month_hb  order by hb desc limit 10");

        hb_1.add(cat_1)
        //println(hb_1)
        return hb_1.get(0)

    }

    def getCategory2Top10MonthHb(String category_1,String month){
        def hb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2, hb  from stg.d_mars_cat2_month_hb where category_1 = ${category_1}  and hb is not null  order by hb desc limit 10");

        hb_2.add(cat_2)
        //println(hb_2)
        return hb_2.get(0)

    }

    def getProductTop10MonthHb(String category_1,String category_2,String month){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name, hb from stg.d_mars_product_month_hb where category_1 = ${category_1} and category_2 = ${category_2} and hb is not null  order by hb desc limit 10");

        pro.add(product)
        //println(pro)
        return pro.get(0)
    }


    def getCategory1Bottom10MonthHb(String month){
        def hb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select * from stg.d_mars_cat1_month_hb  order by hb asc limit 10");

        hb_1.add(cat_1)
        //println(hb_1)
        return hb_1.get(0)

    }


    def getCategory2Bottom10MonthHb(String category_1,String month){
        def hb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2, hb  from stg.d_mars_cat2_month_hb where category_1 = ${category_1}  and hb is not null order by hb asc limit 10 ");

        hb_2.add(cat_2)
        //println(hb_2)
        return hb_2.get(0)

    }

    def getProductBottom10MonthHb(String category_1,String category_2,String month){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name, hb from stg.d_mars_product_month_hb where category_1 = ${category_1} and category_2 = ${category_2} and hb is not null  order by hb asc limit 10");

        pro.add(product)
        //println(pro)
        return pro.get(0)
    }

    def getSingleProductMonthHb(String category_1,String category_2,String product_name,String month){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name, hb from stg.d_mars_product_month_hb where category_1 = ${category_1} and category_2 = ${category_2} and product_name = ${product_name} ");

        pro.add(product)
       //println(pro)
        return pro.get(0)
    }

}
