package com.spiderdt.mars.service

import com.google.gson.Gson
import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

/**
 * Created by chong on 2017/5/12.
 */
@Service
class MarsDeleteSubplanService extends psqlService{
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

    def delete(String name){
        def sub_name = sqlClient.client.executeUpdate("delete from ods.mars_create_subplan where name = ${name}")
        def big_res =  sqlClient.client.rows("select subplan_result from ods.mars_combine_subplan ")*.subplan_result
        //def big_res =  sqlClient.client.rows("select * from ods.mars_combine_subplan ")
        println("big_res:" + big_res)
        println("big_res:" + big_res.class)
        //Boolean isAllowDelete = true
        try {
            big_res.each {
                Gson gson = new Gson()
                def subplan_result = new ArrayList<String>()
                def jsonList = gson.fromJson(it as String, subplan_result.class)

                jsonList.each {
                    //println it
                    if (it == name) {return "success"}
                }
            }

        }catch (Exception e){
            return e.printStackTrace()
        }
    }
}
