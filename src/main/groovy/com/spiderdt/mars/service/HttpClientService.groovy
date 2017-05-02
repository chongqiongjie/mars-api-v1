package com.spiderdt.mars.service

/**
 * @Title:
 * @Package com.spiderdt.mars.service
 * @Description:
 * @author ranran
 * @date 2017/3/15 14:33
 * @version V1.0
 */
import groovy.json.JsonSlurper
import groovyx.net.http.AsyncHTTPBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.XGET
import static groovyx.net.http.Method.POST
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.TEXT
@Service
class HttpClientService {
    private static String clientUrl = "https://192.168.1.2"
    static http = new AsyncHTTPBuilder(poolSize: 30, uri:clientUrl)
    static {
        http.ignoreSSLIssues()
        http.setTimeout(10000)
    }


    def  createToken(String basic, String username, String password) {
        def future = http.request(POST, JSON) { req ->
            headers.clear()
            headers.'Authorization' = "Basic " + basic.bytes.encodeBase64().toString()
            println("createToken headers:"+headers)
            println("Basic headers:"+basic)
            uri.path = '/chain-api-v1/token'
            body = [scopes   : "REPORT-API-V1",
                    auth_type: "password",
                    username : username,
                    password : password]
        }
        while (!future.done) {
            Thread.sleep(100)
        }
        def res =  future.get()
        return res
    }


}
