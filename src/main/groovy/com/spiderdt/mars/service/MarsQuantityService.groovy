package com.spiderdt.mars.service

import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

/**
 * Created by chong on 2017/3/30.
 */
@Service
class MarsQuantityService {
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


    def GetCategory1Top10WeekQuantity(String week){
        def quantity_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select category_1,sum(quantity) as quantity from stg.d_mars_cat1_week_quantity group by category_1 order by quantity desc limit 10")

        quantity_1.add(cat_1)
        return quantity_1
    }

    def GetCategory2Top10WeekQuantity(String category_1,String week){
        def quantity_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2,sum(quantity) as quantity from stg.d_mars_cat1_week_quantity where category_1 = ${category_1} group by category_2 order by quantity desc limit 10")

        quantity_2.add(cat_2)
        return quantity_2
    }

    def GetProductTop10WeekQuantity(String category_1,String category_2,String week){
        def product = new ArrayList()
        def pro = sqlClient.client.rows("select product_name,sum(quantity) as quantity from stg.d_mars_cat1_week_quantity where category_1 = ${category_1} and category_2 = ${category_2} group by product_name order by quantity desc limit 10")

        product.add(pro)
        return  product
    }

    def GetCategory1OtherWeekQuantity(){
        def cat1_other = new ArrayList()
        def cat_1 = sqlClient.client.rows("select  sum(q.quantity) as quantity (select category_1,sum(quantity) as quantity ,row_number() over() as id from stg.d_mars_cat1_week_quantity  group by category_1 order by quantity desc limit 10) q where q.id>10  ")


        cat1_other.add(cat_1)
        return cat1_other
    }

    def GetCategory2OtherWeekQuantity(){
        def cat2_other = new ArrayList()
        def cat_2 = sqlClient.client.rows("select  sum(q.quantity) as quantity (select category_2,sum(quantity) as quantity ,row_number() over() as id from stg.d_mars_cat1_week_quantity where category_1 = ${category_1} group by category_2 order by quantity desc limit 10) q where q.id>10 ")

        cat2_other.add(cat_2)
        return cat2_other
    }

    def GetProductOtherWeekQuantity(){
        def product = new ArrayList()
        def pro = sqlClient.client.rows("select  sum(q.quantity) as quantity (select product_name,sum(quantity) as quantity ,row_number() over() as id from stg.d_mars_cat1_week_quantity where category_1 = ${category_1} and category_2 = ${category_2} group by product_name order by quantity desc limit 10) q where q.id>10 ")

        product.add(pro)
        return product
    }

    def GetCategory1Bottom10WeekQuantity(){
        def quantity_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select category_1,sum(quantity) as quantity from stg.d_mars_cat1_week_quantity group by category_1 order by quantity asc limit 10")

        quantity_1.add(cat_1)
        return quantity_1
    }

    def GetCategory2Bottom10Quantity(){
        def quantity_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2,sum(quantity) as quantity from stg.d_mars_cat1_week_quantity where category_1 = ${category_1} group by category_2 order by quantity asc limit 10")

        quantity_2.add(cat_2)
        return quantity_2
    }

    def GetProductBottom10Quantity(){
        def product = new ArrayList()
        def pro = sqlClient.client.rows("select product_name,sum(quantity) as quantity from stg.d_mars_cat1_week_quantity where category_1 = ${category_1} group by product_name order by quantity desc limit 10")

        product.add(pro)
        return  product
    }

}
