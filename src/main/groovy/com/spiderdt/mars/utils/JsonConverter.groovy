package com.spiderdt.mars.utils

import org.springframework.stereotype.Component

/**
 * @Title:
 * @Package com.spiderdt.mars.utils
 * @Description:
 * @author Kevin
 * @date 2017/3/15 15:48
 * @version V1.0
 */
@Component
class JsonConverter {

    /**
     * 提升相同的层级的数据到detail
     * @param data
     * @return
     */
    HashMap promoteLevel(Object data) {
        def resultMap = new HashMap()

        Object bgs = data.keySet()
        bgs.collect {
            def map2 = data.get(it)
            def bottlers = map2.keySet()
            def newMap2 = new HashMap()
            def newMap3 = new HashMap()
            bottlers.collect { x ->
                if ((x.split("=").last()).equals(it.split("_").last())) {
                    newMap2.put("detail", map2.get(x))
                } else {
                    newMap3.put(x, map2.get(x))
                }
                newMap2.put("children", newMap3)
            }
            resultMap.put(it, newMap2)
        }
        return resultMap
    }

}
