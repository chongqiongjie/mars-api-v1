package com.spiderdt.mars.entity

/**
 * @Title:
 * @Package com.spiderdt.mars.entity
 * @Description:
 * @author Kevin
 * @date 2017/3/15 15:11
 * @version V1.0
 */
abstract class Report {

    String url
    String token
    ArrayList<String> argsList = new ArrayList<>();

    /**
     *
     * @return
     */
    abstract ArrayList<String> getSelectorId()

}
