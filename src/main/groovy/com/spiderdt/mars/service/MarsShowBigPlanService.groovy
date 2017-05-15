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
class MarsShowBigPlanService {
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

    def show(String name){
        def res =  sqlClient.client.rows("select subplan_result from ods.mars_combine_subplan where name = ${name}")
        println("res:" + res.get(0).get("subplan_result"))
        println("res:" + res.get(0).get("subplan_result").class)

        def result = res.get(0).get("subplan_result")
        Gson gson = new Gson()
        def subplan_reult = new ArrayList<String>()

        def jsonList = gson.fromJson(result,subplan_reult.class)
        println("jsonList:" + jsonList)
        //println("jsonList:" + jsonList.collect{it.get("name")})

        String sql = "select result from ods.mars_show_subplan where name in ("
        jsonList.collect{
            def sub_name = it.get("name")
            println("sub_name:" + sub_name)
            sql = sql + "'$sub_name',"
        }

        def  sql_res = sql.substring(0, sql.length() - 1) + ")"
        println("sql_res:" + sql_res)
        def combine_res = sqlClient.client.rows(sql_res)
        println("combine_res:" + combine_res)
        def bigplan_res = combine_res.get(0).get("result")
        println("bigplan_res:" + bigplan_res)

        def map = new HashMap()
        map.put("subplan_info",result)
        map.put("bigplan_result",bigplan_res)
        def list_res = new ArrayList()
        list_res.add(map)
        println("list_res:" + list_res)

        return list_res
    }
}