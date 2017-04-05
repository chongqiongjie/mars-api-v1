package com.spiderdt.mars.service

import com.spiderdt.mars.builder.SelectorArgsBuilder
import com.spiderdt.mars.entity.Report
import com.spiderdt.mars.utils.JsonConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Title:
 * @Package com.spiderdt.mars.service
 * @Description:
 * @author Kevin
 * @date 2017/3/15 17:49
 * @version V1.0
 */
@Service
class BaseService {


    def getDimensionMetrics(Report report) {

        HttpClientService.getListData(report.url, json, report.token)
    }
}
