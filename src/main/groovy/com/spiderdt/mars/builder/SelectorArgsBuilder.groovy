package com.spiderdt.mars.builder

import com.fasterxml.jackson.databind.ObjectMapper
import com.spiderdt.mars.entity.Report
import org.springframework.stereotype.Component

/**
 * @Title:
 * @Package com.spiderdt.mars.builder
 * @Description:
 * @author Kevin
 * @date 2017/3/15 15:32
 * @version V1.0
 */
@Component
class SelectorArgsBuilder {

    String buildSelectorArgs(Report report) {

        def argsList = report.getSelectorId()

        ObjectMapper om = new ObjectMapper()
        HashMap<Object, Object> argsMap = new HashMap<>()
        HashMap<Object, Object> selectorIdsMap = new HashMap<>()

        selectorIdsMap.put("selectorIds", argsList)
        argsMap.put("args", selectorIdsMap)
        om.writeValueAsString(argsMap)
    }

}
