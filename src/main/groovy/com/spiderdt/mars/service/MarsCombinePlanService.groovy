package com.spiderdt.mars.service

import groovy.sql.Sql
import groovyx.net.http.AsyncHTTPBuilder
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.POST

/**
 * Created by chong on 2017/5/8.
 */
@Service
class MarsCombinePlanService {
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



    def
    combine(List nameList){
        println("list:" + nameList)
        String sql = "select result from ods.mars_show_subplan where name in ("
        nameList.collect{
            sql = sql + "'$it',"
            println("sql:" + sql )
        }
        sql = sql.substring(0, sql.length() - 1) + ")"
        println("sql:" + sql)

        def combine =  sqlClient.client.rows(sql)
        println("combine:" + combine)
        return 0
 }


    }

