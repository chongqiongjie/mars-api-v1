package com.spiderdt.mars.service

import groovy.sql.Sql
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.core.io.ClassPathResource

/**
 * Created by qiong on 2017/6/22.
 */
class psqlService {
    protected final String client_key = new ClassPathResource("psql/client.key.pk8").getURI().getPath()
    protected final String client = new ClassPathResource("psql/client.cert.pem").getURI().getPath()
    protected final String root = new ClassPathResource("psql/root.cert.pem").getURI().getPath()

    def sqlClient = openSqlClient("192.168.1.2", "5432", "ms", "spiderdt")

    def openSqlClient(hostname, port, username, password) {
        def client3 = new Sql(new PGPoolingDataSource().each {
            it.url = "jdbc:postgresql://${hostname}:${port}/dw?useSSL=true&ssl=true&characterEncoding=utf-8&stringtype=unspecified&sslmode=require&sslkey=${client_key}&sslcert=${client}&sslrootcert=${root}&sslfactory=org.postgresql.ssl.jdbc4.LibPQFactory".toString()
            //println it.url
            it.user = username
            it.password = password
        })
        [client: client3, args: [hostname: hostname, port: port, username: username, password: password]]

    }
}
