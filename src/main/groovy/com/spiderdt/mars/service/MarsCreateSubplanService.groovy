package com.spiderdt.mars.service

import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.postgresql.util.PSQLException
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

/**
 * Created by chong on 2017/4/13.
 */
@Service
class MarsCreateSubplanService {

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

//    def createSubplan(String name,String category_1,String category_2,String product_name,String start_time,String end_time,String price,String discount,String coupon,String effect_ln_baseprice,String debut){
//        def data
//        try {
//            data =  sqlClient.client.executeInsert("insert into ods.mars_create_subplan (name , category_1 , category_2 , product_name , start_time , end_time , create_time , exec_time, exec_status,price ,discount , coupon ,effect_ln_baseprice ,  debut,is_collected) values (${name},${category_1},${category_2},${product_name},${start_time},${end_time},now()::timestamp(0)without time zone,now()::timestamp(0)without time zone,0,${price},${discount},${coupon},${effect_ln_baseprice},${debut},0)")
//        } catch (PSQLException e) {
//            return [status:"create_failure", message:e.message]
//        }
//        return [status:"create_success", count:data.size()]
//    }


    def createSubplan(String name,String category_1,String category_2,String product_name,String start_time,String end_time,String price,String discount,String coupon,String effect_ln_baseprice,String debut){
        def data
        try {
            data =  sqlClient.client.executeInsert("insert into ods.mars_create_subplan (name , category_1 , category_2 , product_name , start_time , end_time , create_time , exec_time, exec_status,price ,discount , coupon ,effect_ln_baseprice ,  debut,is_collected) values (${name},${category_1},${category_2},${product_name},${start_time},${end_time},now()::timestamp(0)without time zone,now()::timestamp(0)without time zone,0,${price},${discount},${coupon},${effect_ln_baseprice},${debut},0)")
        } catch (PSQLException e) {
            return [status:"create_failure", message:e.message]
        }
        return [status:"create_success", count:data.size()]
    }



}
