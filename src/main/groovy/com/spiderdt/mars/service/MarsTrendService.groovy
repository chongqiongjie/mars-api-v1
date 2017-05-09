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
        def client = new Sql(new PGPoolingDataSource().each {
            it.url = "jdbc:postgresql://${hostname}:${port}/dw?useSSL=true&ssl=true&characterEncoding=utf-8&stringtype=unspecified&sslmode=require&sslkey=${client_key}&sslcert=${client}&sslrootcert=${root}&sslfactory=org.postgresql.ssl.jdbc4.LibPQFactory".toString()
            it.user = username
            it.password = password
        })
        [client: client, args: [hostname:hostname, port:port, username:username, password:password]]
    }





//    def getCategory(){
//        def a =  sqlClient.client.rows("select distinct category_1 from stg.d_mars_final_result ")*.category_1.unique();
//        def data = new ArrayList()
//        for(int i = 0; i < a.size(); i ++) {
//            def b = sqlClient.client.rows("select distinct category_2 from stg.d_mars_final_result where category_1  = ${a[i]}")*.category_2.unique() ;
//            def cat_1 = new HashMap();
//            cat_1.put("name", a[i]);
//            def children = new ArrayList();
//            for(int j = 0; j < b.size(); j ++) {
//                def c = sqlClient.client.rows("select distinct product_name from stg.d_mars_final_result where category_1 = ${a[i]} and category_2 = ${b[i]}")*.product_name.unique() ;
//                def cat_2 = new HashMap();
//                cat_2.put("name", b[j]);
//                cat_2.put("children", c);
//                children.add(cat_2);
//            }
//            cat_1.put("children", children);
//            data.add(cat_1);
//        }
//        println(data);
//        return data
//    }


    def getCategory() {
        def a =  sqlClient.client.rows("select cat1_id,category_1,cat2_id,category_2,ppg_id,product_name from stg.mars_all_id GROUP BY cat1_id,category_1,cat2_id,category_2,ppg_id,product_name");

        def map1 = [:]
        def cat1 = new HashMap()
        def children = new ArrayList()
        a.collect {
            [[id:it.get("cat1_id"), name:it.get("category_1")],[ cat2_id:it.get("cat2_id"), category_2:it.get("category_2"),ppg_id:it.get("ppg_id"), product_name:it.get("product_name")]]
        }.groupBy {
           it.get(0)
        }.each { k,v ->
           // println("k1:" + k)
            cat1.put("item", k)
            map1[cat1] = v.collect{
              // println("v1:" + it.get(1))
                it.get(1)
            }
        }

        def map2 = [:]
        def pro = new ArrayList()
        map1.each {
            def tmpMap = [:]
            def cat2 = new HashMap()
             println("it:" + it.value)
            // 对 [cat2_id, category_2, pro_id, product_name] 进行分组后 groupby 结果放到 tmpMap 中
            it.value.collect {
               [[id: it.get("cat2_id"), name:it.get("category_2")], [id:it.get("ppg_id"), name:it.get("product_name")]]
            }.groupBy {
               it.get(0)
            }.each {  k,v ->
                //println("k:" + k)
                cat2.put("item", k)
                cat2.put("children",v.collect{it[1]})
                pro.add(cat2)
                //println("pro:" + pro)
                tmpMap[cat2] = v.collect{
                    [ it.get(1)]
                    //println("sss:"+ it.get(1))
                }
            }
            map2[it.key] = tmpMap
        }
        cat1.put("children",pro)
       // println("cat1:" + cat1)
        children.add(cat1)
        println("children1:"+ children)
        return children


    }




    def getCategory1Score(String category_1)  {
        def score_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select q.date, q.actual_date,sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date,actual_date,category_2, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.mars_all_id where cat1_id = ${category_1}  group by date,category_2,actual_date order by date) q group by date,actual_date order by date");


        score_1.add(cat_1)
        //println(score_1)
        return score_1.get(0)
    }


    def getCategory2Score(String category_1,String category_2){
        def score_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select q.date,q.actual_date,sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date, actual_date,product_name,sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.mars_all_id where cat1_id = ${category_1} and cat2_id = ${category_2} group by date,product_name,actual_date order by date) q group by date ,actual_date order by date ") ;

        score_2.add(cat_2)
        return score_2.get(0)
    }


    def getProductScore(String category_1,String category_2, String product_name){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select  date,actual_date, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.mars_all_id where cat1_id = ${category_1} and cat2_id = ${category_2} and pro_id = ${product_name} group by date,actual_date order by date") ;


        pro.add(product)
        //println("pro:" + pro)
        return pro.get(0)
    }


    def getAllScore(){
        def all = new ArrayList()
        def a = sqlClient.client.rows("select q.date, q.actual_date,sum(q.quantity) as quantity,sum(q.effect_ln_baseprice) as effect_ln_baseprice,sum(q.effect_debut) as effect_debut ,sum(q.effect_discount) as effect_discount,sum(q.effect_coupon) as effect_coupon,sum(q.other) as other,sum(actual) as actual from (select date,actual_date,category_1, sum(quantity) as quantity,sum(effect_ln_baseprice) as effect_ln_baseprice,sum(effect_debut) as effect_debut ,sum(effect_discount) as effect_discount,sum(effect_coupon) as effect_coupon,sum(other) as other,sum(actual) as actual from stg.mars_all_id  group by date,category_1,actual_date order by date) q group by date,actual_date order by date") ;
        all.add(a)
        return all.get(0)
    }


}
