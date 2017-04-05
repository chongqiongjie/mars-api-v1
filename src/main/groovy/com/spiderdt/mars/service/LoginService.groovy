package com.spiderdt.mars.service

/**
 * @Title:
 * @Package com.spiderdt.mars.service
 * @Description:
 * @author ranran
 * @date 2017/3/15 15:44
 * @version V1.0
 */

import com.spiderdt.mars.utils.AddSHA1
import groovyx.net.http.HttpResponseException
import java.text.SimpleDateFormat;

public class LoginService {
    private static String checkAuthoUrl = "/auth-api-v1/user/token";
    private static final TOKEN = "48cea90d66af40c8b0ccb382e627d996"


    public static Object login(String username, String password) throws Exception {
        def s = HttpClientService.createToken("mars-api-v1:spiderdt.com", username, password)
    }

    public static String getToken() {
        login("chong", "spiderdt")
    }


    public static Object checkOauth(String token) {
        Object username = null;
        try {
            Map access = HttpClientService.getAccess(checkAuthoUrl, token);
            username = access.get("username");
        } catch (HttpResponseException ignored) {
            Thread.sleep(100)
            Map access = HttpClientService.getAccess(checkAuthoUrl, token);
            username = access.get("username");
        } catch (Exception e) {
            username = null;
            println("unAuth is    " + e.getMessage())
            e.printStackTrace()
        }
        return username
    }

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyyMMddhh:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        def stamp = Long.parseLong(timestamp)

        def curTime = dateFormatGmt.parse(dateFormatGmt.format(new Date())).getTime() / 1000
        println("log: curDate is " + (dateFormatGmt.format(new Date())))
        println("log: curTime - stamp is " + (curTime - stamp))
        if (curTime - stamp <= 30) {
            String token2 = TOKEN
            def arr = [token2, timestamp, nonce] as String[]
            Arrays.sort(arr);
            String str = arr[0] + "" + arr[1] + "" + arr[2]
            String hash = AddSHA1.SHA1(str)
            hash = hash.replace("-", "")
            if (hash.equals(signature)) {
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }
}
