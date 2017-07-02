package com.spiderdt.mars.service

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.sql.Sql
import groovyx.net.http.AsyncHTTPBuilder
import groovyx.net.http.HttpResponseException
import org.json.JSONObject
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.POST

/**
 * Created by chong on 2017/4/28.
 */
@Service
class MarsExecuteCreateService extends psqlService{
//    private final String client_key = new ClassPathResource("psql/client.key.pk8").getURI().getPath()
//    private final String client = new ClassPathResource("psql/client.cert.pem").getURI().getPath()
//    private final String root = new ClassPathResource("psql/root.cert.pem").getURI().getPath()
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


    private static String clientUrl = "http://192.168.1.187"
    private static String clientPortUrl = clientUrl + ":9123"

    static http = new AsyncHTTPBuilder(poolSize: 30, uri:clientPortUrl)
    static {
        http.ignoreSSLIssues()
    }


    def create(url,name,category,group,product,start_time,end_time,Map drivers){
        def future = http.request(POST, JSON) { req ->
            uri.path = url
            body = [name:name,
                    category:category,
                    group:group,
                    product:product,
                    start_time:start_time,
                    end_time:end_time,
                    drivers: drivers.collectEntries {[it.key, it.value/100]}
            ]
            println("body:"+ body)
            //println("url:"+ url)
            println("d:"+ drivers.collectEntries {[it.key, it.value/100]})
        }
        while (!future.done){
            Thread.sleep(100)
        }
        try{
            def res = future.get()
            ObjectMapper mapper = new ObjectMapper()
            def resJson = mapper.writeValueAsString(res)
            println(resJson)
            if(res.toString().startsWith("ERROR")){
                def a =  sqlClient.client.executeUpdate("update ods.mars_create_subplan set exec_status = '0' where exec_status = '1' and name = ${name}")
                println("a:" + a)
            }else {
                def b =  sqlClient.client.executeUpdate("update ods.mars_create_subplan set exec_status = '2' where exec_status = '1' and name = ${name}")
                def insert = sqlClient.client.executeInsert("insert into ods.mars_show_subplan (name,result) values (${name},${resJson})")
                println("b:" + b)
                println("insert:" + insert)
            }
        }catch (Exception e){
            println(e.printStackTrace())
            def c =  sqlClient.client.executeUpdate("update ods.mars_create_subplan set exec_status = '0' where exec_status = '1' and name = ${name}")
            println("c:" +  c)
        }
    }


}