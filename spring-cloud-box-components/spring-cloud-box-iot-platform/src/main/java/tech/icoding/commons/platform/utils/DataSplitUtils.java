package tech.icoding.commons.platform.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : dyq
 * @Description: 根据传入的list，判断list数据的大小，拆分成每个内存不大于1M的list，
 * 返回一个如下map：
 * {"BATCHNUM"：设置请求唯一标识，
 * "TOTALNUM"：设置数据拆分条数，
 * "LINENUM"：设置当前拆分行号，
 * "ASNDETAIL"：设置详细表}
 * @date Date : 2021年07月2日
 */
public class DataSplitUtils {
    public static List<Map<String,Object>> getResult(String businessCode,List<Map<String,Object>> list){
        String str = JSON.toJSONString(list);
        int total = list.size();
        int threadNum = (str.getBytes().length) / 1024 / 1024 + 1;//计算数据拆分条数
        int remaider = total % threadNum; // 计算出余数
        int number = total / threadNum; // 计算出商
        int offset = 0;// 偏移量
        String batchNum = String.valueOf(((new SnowflakeId(threadNum)).nextId()));//生成请求唯一标识18位数
        List<Map<String,Object>> mapList =new ArrayList<Map<String,Object>>();
        for (int i = 0; i < threadNum; i++) {
            if (remaider > 0) {
                List<Map<String,Object>> subList = list.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
                Map<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("BATCHNUM",String.valueOf(batchNum));//设置请求唯一标识String.valueOf(batchNum)
                hashMap.put("TOTALNUM",String.valueOf(threadNum));//设置数据拆分条数
                hashMap.put("LINENUM",String.valueOf(i + 1));//设置当前拆分行号
                hashMap.put("BUSINESSCODE",businessCode);
                hashMap.put("ASNDETAIL",subList);//设置详细表(ASNDETAIL)
                mapList.add(hashMap);
            } else {
                List<Map<String,Object>> subList = list.subList(i * number + offset, (i + 1) * number + offset);
                Map<String, Object> hashMap = new HashMap<String, Object>();
                hashMap.put("BATCHNUM",String.valueOf(batchNum));
                hashMap.put("TOTALNUM",String.valueOf(threadNum));
                hashMap.put("LINENUM",String.valueOf(i + 1));
                hashMap.put("ASNDETAIL",subList);
                hashMap.put("BUSINESSCODE",businessCode);
                mapList.add(hashMap);
            }
        }
        return mapList;
    }
}
