package com.spiderdt.mars.controller

import com.spiderdt.mars.service.HttpClientService
import com.spiderdt.mars.service.LoginService
import com.spiderdt.mars.vo.User
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletRequest

@Controller
public class LoginController {

    // getToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody User user) throws Exception {
        JSONObject json = new JSONObject();
        String token = null
        String basic = "mars-api-v1:spiderdt.com"
        try {
            println("********************************begin")
            String username = user.username
            String password = user.password
            System.out.println("username is " + username + "     password is " + password);
            Object map = HttpClientService.createToken(basic, username, password);
            println("info is " + map)
            println("token is " + map.get("id"))
            if (map.get("id") != null) {
                token = map.get("id")
                json.put("status", "success");
                json.put("access_token", token);
            } else {
                json.put("status", "unAuth");
                json.put("message1", map)
            }
        } catch (Exception e) {
            json.put("message", e.message);
            json.put("status", "failure");
        }
        return ResponseEntity.status(HttpStatus.OK).body(json.toString());
    }

    // getToken
    @RequestMapping(value = "/check_signature", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> checkOauth(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        try {
            String signature = request.getParameter("signature")
            String timestamp = request.getParameter("timestamp")
            String nonce = request.getParameter("nonce")
            String username = request.getParameter("username")
            boolean checkOauth = LoginService.checkSignature(signature, timestamp, nonce)
            if (checkOauth == true) {
                String token = OauthManager.getToken()
                json.put("status", "success");
                json.put("access_token", token);
                json.put("username", username);
            } else {
                json.put("status", "unAuth");
            }
        } catch (Exception e) {
            json.put("message", e.message);
            if (e.message == "unAuth") {
                json.put("status", "unAuth");
            } else {
                json.put("status", "failure");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(json.toString());
    }


}
