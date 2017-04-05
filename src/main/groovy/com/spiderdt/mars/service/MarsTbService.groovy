package com.spiderdt.mars.service

import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

/**
 * Created by chong on 2017/3/24.
 */
@Service
class MarsTbService {
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


    def getCategory1Tb(){
        def tb_1 = new ArrayList()
        def cat_1 = sqlClient.client.rows("select * from stg.d_mars_cat_1_tb ");


        tb_1.add(cat_1)
        println(tb_1)
        return tb_1.get(0)

    }


    def getCategory2Tb(String category_1){
        def tb_2 = new ArrayList()
        def cat_2 = sqlClient.client.rows("select category_2,sum(tb) as tb  from stg.d_mars_cat_2_tb where category_1 = ${category_1} group by category_2 order by category_2 desc limit 10  ");

        tb_2.add(cat_2)
        println(tb_2)
        return tb_2.get(0)

    }

    def getProductTb(String category_1,String category_2){
        def pro = new ArrayList()
        def product = sqlClient.client.rows("select product_name,sum(tb) as tb from stg.d_mars_product_tb where category_1 = ${category_1} and category_2 = ${category_2}  group by product_name order by product_name desc limit 10");

        pro.add(product)
        println(pro)
        return pro.get(0)

    }
}
