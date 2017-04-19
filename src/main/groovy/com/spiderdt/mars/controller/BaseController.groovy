package com.spiderdt.mars.controller

import com.spiderdt.mars.handler.AbstractDataHandler
import com.spiderdt.mars.service.CommonService
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

/**
 * @Title:
 * @Package com.spiderdt.mars.controller
 * @Description:
 * @author Kevin
 * @date 2017/3/17 9:44
 * @version V1.0
 */
@Controller
abstract class BaseController {

    @Autowired
    CommonService commonService

    def processDataTemplate(AbstractDataHandler handler) {
        JSONObject response = new JSONObject()
        try {

            handler.process()

        } catch (Exception e) {
            response.put("message", e.message)
            if (e.message == "unAuth") {
                response.put("status", "unAuth")
            } else {
                response.put("status", "failure")
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())
    }

    def getCategoryTemplate(String url, String token) {
        JSONObject response = new JSONObject()
        try {
            Object category = commonService.getCategory(url, token)
            response.put("status", "success")
            response.put("category", category)

        } catch (Exception e) {
            response.put("message", e.message)
            if (e.message == "unAuth") {
                response.put("status", "unAuth")
            } else {
                response.put("status", "failure")
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(response.toString())
    }

}
