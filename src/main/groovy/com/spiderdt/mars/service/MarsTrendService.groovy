package com.spiderdt.mars.service

import com.spiderdt.mars.entity.MarsTrend
import org.postgresql.ds.PGSimpleDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource

import javax.servlet.Servlet
import javax.servlet.ServletContext
import javax.swing.text.html.HTML

/**
 * Created by chong on 2017/3/17.
 */
@Service
class MarsTrendService extends BaseService {
//    @Value('${psql.client_key}') String client_key
//    @Value('${psql.client}') String client
//    @Value('${psql.root}') String root

    private final String client_key = new ClassPathResource("psql/client.key.pk8").getURI().getPath()
    private final String client = new ClassPathResource("psql/client.cert.pem").getURI().getPath()
    private final String root = new ClassPathResource("psql/root.cert.pem").getURI().getPath()


//   def sqlClient = openSqlClient("192.168.1.3", "5432", "ms", "spiderdt","C:\\setup\\pgadmin_ssl_pk8\\client.key.pk8","C:\\setup\\pgadmin_ssl_pk8\\client.cert.pem","C:\\setup\\pgadmin_ssl_pk8\\root.cert.pem")
//    def openSqlClient(hostname, port, username, password,client_key,client,root) {
    def sqlClient = openSqlClient("192.168.1.3", "5432", "ms", "spiderdt")
    def openSqlClient(hostname, port, username, password) {
        def client3 = new Sql(new PGPoolingDataSource().each {
            it.url = "jdbc:postgresql://${hostname}:${port}/dw?useSSL=true&ssl=true&characterEncoding=utf-8&stringtype=unspecified&sslmode=require&sslkey=${client_key}&sslcert=${client}&sslrootcert=${root}&sslfactory=org.postgresql.ssl.jdbc4.LibPQFactory".toString()
            it.user = username
            it.password = password
        })
        [client: client3, args: [hostname:hostname, port:port, username:username, password:password]]
    }


//    def getCategory(){
//        def cat1 = new HashMap()
//        def cat2 = new HashMap()
//
//        def cat1_data = new ArrayList()
//        def cat2_data = new ArrayList()
//
//        def a =  sqlClient.client.rows("select distinct  category_1 from stg.d_mars_order_date_score ")*.category_1.unique() ;
//        def b = sqlClient.client.rows("select distinct category_2 from stg.d_mars_order_date_score ")*.category_2.unique() ;
//        def c = sqlClient.client.rows("select distinct product_name from stg.d_mars_order_date_score ")*.product_name.unique() ;
//        def cat_1 = a.collect{it}
//        cat1.put("name",cat_1)
//        def cat_2 = b.collect{it}
//        cat2.put("name",cat_2)
//        def product = c.collect{it}
//        cat2.put("children",product)
//        cat2_data.add(cat2)
//
//        cat1.put("children",cat2_data)
//        cat1_data.add(cat1)
//
//        return cat1_data
//
//    }

    def getCategory(){
        def a =  sqlClient.client.rows("select distinct  category_1 from stg.d_mars_final_result ")*.category_1.unique() ;
        def data = new ArrayList()
        for(int i = 0; i < a.size(); i ++) {
            def b = sqlClient.client.rows("select distinct category_2 from stg.d_mars_final_result where category_1=${a[i]}")*.category_2.unique() ;
            def cat_1 = new HashMap();
            cat_1.put("name", a[i]);
            def children = new ArrayList();
            for(int j = 0; j < b.size(); j ++) {
                def c = sqlClient.client.rows("select distinct product_name from stg.d_mars_final_result where category_1=${a[i]} and category_2=${b[j]}")*.product_name.unique() ;
                def cat_2 = new HashMap();
                cat_2.put("name", b[j]);
                cat_2.put("children", c);
                children.add(cat_2);
            }
            cat_1.put("children", children);
            data.add(cat_1);
        }
       //println(data);
        return data
    }

    def getCategory1Score(String category_1)  {
        def score_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select q.date, sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date,category_2, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.d_mars_final_result where category_1 = ${category_1}  group by date,category_2 order by date) q group by date order by date");

        score_1.add(cat_1)
        //println(score_1)
        return score_1.get(0)
    }


    def getCategory2Score(String category_1,String category_2){
        def score_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select q.date, sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date, product_name,sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.d_mars_final_result where category_1 = ${category_1} and category_2 = ${category_2} group by date,product_name order by date) q group by date order by date ") ;

        score_2.add(cat_2)
        return score_2.get(0)
    }


    def getProductScore(String category_1,String category_2, String product_name){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select  date, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.d_mars_final_result where category_1 = ${category_1} and category_2 = ${category_2} and product_name = ${product_name} group by date order by date") ;


        pro.add(product)
        //println("pro:" + pro)
        return pro.get(0)
    }


    def getAllScore(){
        def all = new ArrayList()
        def a = sqlClient.client.rows("select q.date, sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date,category_1, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.d_mars_final_result  group by date,category_1 order by date) q group by date order by date") ;
        all.add(a)
        return all.get(0)
    }


}
