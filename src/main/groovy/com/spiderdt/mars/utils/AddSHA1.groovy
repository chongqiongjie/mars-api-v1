package com.spiderdt.mars.utils

/**
 * @Title:
 * @Package com.spiderdt.mars.util
 * @Description:选择SHA-1加密
 * @author ranran
 * @date 2017/3/15 15:01
 * @version V1.0
 */

import java.security.*;

public class AddSHA1 {
    public static String SHA1(String inStr) {
        def md = null
        def outStr = null
        try {
            md = MessageDigest.getInstance("SHA-1")
            def digest = md.digest(inStr.getBytes())
            outStr = byteToString(digest)
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace()
        }
        outStr
    }

    public static String byteToString(byte[] digest) {
        def str = ""
        def tempStr = ""
        for (i in 0 .. digest.length-1) {
            tempStr = (Integer.toHexString(digest[i] & 0xff))
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr
            } else {
                str = str + tempStr;
            }
        }
        str.toLowerCase()
    }
}
