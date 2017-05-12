package com.spiderdt.mars.service

import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

/**
 * Created by chong on 2017/4/12.
 */
@Service
class MarsTrendAreaService {
    private final String client_key = new ClassPathResource("psql/client.key.pk8").getURI().getPath()
    private final String client = new ClassPathResource("psql/client.cert.pem").getURI().getPath()
    private final String root = new ClassPathResource("psql/root.cert.pem").getURI().getPath()


    def sqlClient = openSqlClient("192.168.1.3", "5432", "ms", "spiderdt")
    def openSqlClient(hostname, port, username, password) {
        def client3 = new Sql(new PGPoolingDataSource().each {
            it.url = "jdbc:postgresql://${hostname}:${port}/dw?useSSL=true&ssl=true&characterEncoding=utf-8&stringtype=unspecified&sslmode=require&sslkey=${client_key}&sslcert=${client}&sslrootcert=${root}&sslfactory=org.postgresql.ssl.jdbc4.LibPQFactory".toString()
            it.user = username
            it.password = password
        })
        [client: client3, args: [hostname:hostname, port:port, username:username, password:password]]
    }

    def getCategory1Score(String start_time,String end_time){
        def cat_1 = new ArrayList()
        def category_1 = sqlClient.client.rows("select q.date, q.actual_date,sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date,actual_date,category_1, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.mars_all_id where date between  ${start_time} and ${end_time} group by date,category_1,actual_date order by date) q group by date,actual_date order by date") ;
        println("cat_1:" + category_1)
        //println("sql:" + "select q.date, sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date,actual_date,category_1, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.d_mars_final_result where date between  '${start_time}' and '${end_time}' group by date,actual_date ,category_1 order by date) q group by date order by date")
        cat_1.add(category_1)
        return cat_1.get(0)
    }


    def getCategory2Score(String category_1,String start_time,String end_time){
        def cat_2 = new ArrayList()
        def category_2 = sqlClient.client.rows("select q.date, q.actual_date,sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date,actual_date,category_2, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.mars_all_id where cat1_id = ${category_1} and date between  ${start_time} and ${end_time} group by date,category_2,actual_date order by date) q group by date,actual_date order by date") ;

         cat_2.add(category_2)
        //println(score_1)
        return cat_2.get(0)
    }

    def getProductScore(String category_1,String category_2,String start_time,String end_time){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select q.date, q.actual_date,sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date,actual_date,product_name, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.mars_all_id where cat1_id = ${category_1} and cat2_id = ${category_2} and date between  ${start_time} and ${end_time} group by date,product_name,actual_date order by date) q group by date,actual_date order by date") ;

        pro.add(product)
        return pro.get(0)
    }

    def getSingleProductScore(String category_1,String category_2,String product_name,String start_time,String end_time){
        def single = new ArrayList()
        def single_product = sqlClient.client.rows("select date,actual_date, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.mars_all_id where cat1_id = ${category_1} and cat2_id = ${category_2} and ppg_id = ${product_name} and date between  ${start_time} and ${end_time} group by date,actual_date order by date") ;

        single.add(single_product)
        println("single_product:" + single)
        return single.get(0)
    }



    def getCategory1MaxmonthScore() {
        def cat_1 = new ArrayList()
        def cat1_max = sqlClient.client.rows("select avg(effect_ln_baseprice) as effect_ln_baseprice,avg(effect_debut) as effect_debut,avg(effect_discount) as effect_discount,avg(effect_coupon) as effect_coupon from  ods.mars_max_month_avg_cat1 ")

        cat_1.add(cat1_max)
        println("max_cat1" + cat_1)
        return cat_1.get(0)[0]
    }

    def getCategory2MaxmonthScore(String cat1_id) {
        def cat_2 = new ArrayList()
        def cat2_max = sqlClient.client.rows("select avg(effect_ln_baseprice) as effect_ln_baseprice,avg(effect_debut) as effect_debut,avg(effect_discount) as effect_discount,avg(effect_coupon) as effect_coupon from  ods.mars_max_month_avg_cat2  where cat1_id = ${cat1_id}")

        cat_2.add(cat2_max)
        println("max_cat2" + cat_2)
        return cat_2.get(0)[0]
    }

    def getProductMaxmonthScore(String cat1_id,String cat2_id) {
        def pro = new ArrayList()
        def pro_max = sqlClient.client.rows("select avg(effect_ln_baseprice) as effect_ln_baseprice,avg(effect_debut) as effect_debut,avg(effect_discount) as effect_discount,avg(effect_coupon) as effect_coupon from  ods.mars_max_month_avg_product  where cat1_id = ${cat1_id} and cat2_id= ${cat2_id} ")

        pro.add(pro_max)
        println("pro_max" + pro)
        return pro.get(0)[0]
    }

    def getSingleProductMaxmonthScore(String cat1_id,String cat2_id,String ppg_id){
        def pro_single = new ArrayList()
        def pro_max = sqlClient.client.rows("select avg(effect_ln_baseprice) as effect_ln_baseprice,avg(effect_debut) as effect_debut,avg(effect_discount) as effect_discount,avg(effect_coupon) as effect_coupon from  ods.mars_max_month_avg_single_product  where cat1_id = ${cat1_id} and cat2_id = ${cat2_id} and ppg_id = ${ppg_id} ")

        pro_single.add(pro_max)
        println("pro_max11111" + pro_single)
        return pro_single.get(0)[0]
    }

}
