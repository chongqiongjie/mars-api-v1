package com.spiderdt.mars.service

/**
 * Created by chong on 2017/3/20.
 */
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @Title:
 * @Package com.spiderdt.cocacola.service
 * @Description:
 * @author Kevin
 * @date 2017/3/16 10:45
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-web.xml")
class BaseTest extends GroovyTestCase{

    @Autowired
    HttpClientService httpClientService

    @Value('${createToken.basic}') String basic
    @Value('${createToken.username}') String username
    @Value('${createToken.password}') String password

    String token = ""

    @Before
    void setUp() {
        token =  httpClientService.createToken(basic, username, password).id
    }
}