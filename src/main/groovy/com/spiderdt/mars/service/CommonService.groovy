package com.spiderdt.mars.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat;

@Service
public class CommonService {

    // get category list
    public static Object getCategory(String getCategoryUrl, String token) throws Exception {
        // TODO ！！！ 后面改为树形结构
        Object data = HttpClientService.getData(getCategoryUrl, token);

        return data;
    }

    // 传list得到category
    public static Object getListCategory(String getCategoryUrl, List reports, String token) throws Exception {
        // ！！！ 后面改为树形结构
        ObjectMapper om = new ObjectMapper();
        HashMap<Object, Object> argsMap = new HashMap<>();
        HashMap<Object, Object> selectorIdsMap = new HashMap<>();
        ArrayList<String> argsList = new ArrayList<>();

        for (String report : reports) {
            argsList.add(report);
        }

        selectorIdsMap.put("reportIds", argsList);
        argsMap.put("args", selectorIdsMap);
        String json = om.writeValueAsString(argsMap);
        System.out.println(json);
        Object data = HttpClientService.getSingleData(getCategoryUrl, json, token);

        return data;
    }

    // 获得选择时间（包含）以前的数据
    public static Object beforeData(List data, String month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map o = (Map) data.get(0);
        Set dateSet = o.keySet();
        HashMap infoMap = new HashMap();
        for (Object key : dateSet) {
            String dateString = key.toString().split("=")[1];
            Object value = o.get(key);
            //取出数据库中的时间
            Date date1 = sdf.parse(dateString);
            //前段传过来的时间
            Date date2 = sdf.parse(month);
            if (date1.after(date2)) {
            } else {
                infoMap.put(key, value);
            }
        }
        return infoMap;
    }

// 获得选择时间（包含）以前的数据
    public static Object beforeMergeData(List data, ArrayList<String> bottlers, String month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Set keys = new HashSet();
        HashMap infoMap = new HashMap();
        for (Object o : data) {
            // 取到所有的channel/kpi作为key
            for (Object key : ((Map) o).keySet()) {
                keys.add(key);
            }
        }
        for (int i = 0; i < data.size(); i++) {
            HashMap m = (HashMap) data.get(i);
            for (String bottler : bottlers) {
                String bottlerKey = "bottler=" + bottler;
                HashMap value = (HashMap) m.get(bottlerKey);
                if (value != null) {
                    HashMap dateMap = new HashMap();
                    for (Object date : value.keySet()) {
                        String dateString = date.toString().split("=")[1];
                        Object value2 = value.get(date);
                        //取出数据库中的时间
                        Date date1 = sdf.parse(dateString);
                        //前段传过来的时间
                        Date date2 = sdf.parse(month);
                        if (date1.after(date2)) {
                        } else {
                            dateMap.put(date, value2);
                        }

                    }
                    infoMap.put(bottlerKey, dateMap)
                }
            }
        }


        println("infoMap is " + infoMap)
        return infoMap;
    }


}
