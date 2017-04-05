package com.spiderdt.mars.service

import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

/**
 * Created by chong on 2017/3/28.
 */
@Service
class MarsHbService  {

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


    def getCategory1Top5WeekHb(String week){
        def hb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select category_1,sum(hb) as hb from stg.d_mars_cat1_week_hb group by category_1 order by hb desc limit 10");

        hb_1.add(cat_1)
        println(hb_1)
        return hb_1.get(0)

    }

    def getCategory2Top5WeekHb(String category_1,String week){
        def hb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2,sum(hb) as hb  from stg.d_mars_cat2_week_hb where category_1 = ${category_1}  and hb is not null group by category_2  order by hb desc limit 10 ");

        hb_2.add(cat_2)
        println(hb_2)
        return hb_2.get(0)

    }

    def getProductTop5WeekHb(String category_1,String category_2,String week){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name,sum(hb) as hb from stg.d_mars_product_week_hb where category_1 = ${category_1} and category_2 = ${category_2} and hb is not null group by product_name order by hb desc limit 10");

         pro.add(product)
         println(pro)
         return pro.get(0)
    }


    def getCategory1Bottom5WeekHb(String week){
        def hb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select * from stg.d_mars_cat1_week_hb order by hb limit 10");

        hb_1.add(cat_1)
        println(hb_1)
        return hb_1.get(0)

    }


    def getCategory2Bottom5WeekHb(String category_1,String week){
        def hb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2,sum(hb) as hb  from stg.d_mars_cat2_week_hb where category_1 = ${category_1} and hb is not null group by category_2 order by hb asc limit 10 ");

        hb_2.add(cat_2)
        println(hb_2)
        return hb_2.get(0)

    }

    def getProductBottom5WeekHb(String category_1,String category_2,String week){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name,sum(hb) as hb from stg.d_mars_product_week_hb where category_1 = ${category_1} and category_2 = ${category_2} and hb is not null group by product_name order by hb asc limit 10");

        pro.add(product)
        println(pro)
        return pro.get(0)
    }








    def getCategory1Top5MonthHb(String month){
        def hb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select category_1,sum(hb) as hb from stg.d_mars_cat1_month_hb group by category_1 order by hb desc limit 10");

        hb_1.add(cat_1)
        println(hb_1)
        return hb_1.get(0)

    }

    def getCategory2Top5MonthHb(String category_1,String month){
        def hb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2,sum(hb) as hb  from stg.d_mars_cat2_month_hb where category_1 = ${category_1}  and hb is not null group by category_2  order by hb desc limit 10");

        hb_2.add(cat_2)
        println(hb_2)
        return hb_2.get(0)

    }

    def getProductTop5MonthHb(String category_1,String category_2,String month){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name,sum(hb) as hb from stg.d_mars_product_month_hb where category_1 = ${category_1} and category_2 = ${category_2} and hb is not null group by product_name order by hb desc limit 10");

        pro.add(product)
        println(pro)
        return pro.get(0)
    }


    def getCategory1Bottom5MonthHb(String month){
        def hb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select * from stg.d_mars_cat1_month_hb order by hb limit 10");

        hb_1.add(cat_1)
        println(hb_1)
        return hb_1.get(0)

    }


    def getCategory2Bottom5MonthHb(String category_1,String month){
        def hb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2,sum(hb) as hb  from stg.d_mars_cat2_month_hb where category_1 = ${category_1} and hb is not null group by category_2 order by hb asc limit 10 ");

        hb_2.add(cat_2)
        println(hb_2)
        return hb_2.get(0)

    }

    def getProductBottom5MonthHb(String category_1,String category_2,String month){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name,sum(hb) as hb from stg.d_mars_product_month_hb where category_1 = ${category_1} and category_2 = ${category_2} and hb is not null group by product_name order by hb asc limit 10");

        pro.add(product)
        println(pro)
        return pro.get(0)
    }

}
