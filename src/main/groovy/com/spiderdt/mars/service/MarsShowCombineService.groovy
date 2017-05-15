package com.spiderdt.mars.service

import com.google.gson.Gson

import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

/**
 * Created by chong on 2017/5/11.
 */
@Service
class MarsShowCombineService {
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

    def show(){
       def res =  sqlClient.client.rows("select name,subplan_result from ods.mars_combine_subplan")
       // println("res:" + res.get(0).get("subplan_result"))
        //println("res:" + res.get(0).get("subplan_result").class)
        def big_name = res.get(0).get("name")
        def sub_name = res.get(0).get("subplan_result")

        Gson gson = new Gson()
        def name = new ArrayList<String>()
        def a = gson.fromJson(sub_name, name.class)



        def sql  = "select name,start_time,end_time,create_time,exec_time,exec_status from  ods.mars_create_subplan where name in ("
        a.collect{
            println("it:"+ it)
            sql = sql + "'$it',"
        }
        def sql_res = sql.substring(0, sql.length() - 1) + ")"
        def sql_result = sqlClient.client.rows(sql_res)
        //println("sql_result:" + sql_result)
        def map = new HashMap()
        map.put("bigplan_name",big_name)
        map.put("subplan_info",sql_result)
        def list = new ArrayList()
        list.add(map)
        return  list
    }
}
