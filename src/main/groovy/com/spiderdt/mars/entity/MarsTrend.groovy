package com.spiderdt.mars.entity

/**
 * Created by chong on 2017/3/17.
 */
final class MarsTrend extends Report{

    String category_1
    String category_2
    String product_name

    @Override
    ArrayList<String> getSelectorId() {
        def selectorId = "[\"category_1=" + category_1 + "\",\"category_2=" + category_2 + "\",\"product_name=" + product_name + "\"]"
        argsList.add(selectorId)
        return  argsList
    }
}
