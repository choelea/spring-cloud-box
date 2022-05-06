package tech.icoding.commons.tools.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * Excel工具
 * @author lxy
 */
public class ExcelTool<T> {

    /**
     * 导出数据
     * @param param 请求参数
     * @return 导出数据的字节
     */
    public byte[] exportDataToExcel(ExcelExportParam param){
        Map<String,String> head = param.getFields();
        List<?> dataList = param.getDataList();
        String json = JSON.toJSONString(dataList);
        List<T> convertDataList = JSON.parseObject(json,new TypeReference<List<T>>(){});
        //排序
        sorted(convertDataList);
        //排序后的再次转换
        json = JSON.toJSONString(convertDataList);
        List<Map<String,Object>> dataMapList =
                JSON.parseObject(json,new TypeReference<List<Map<String,Object>>>(){});
        return DynamicEasyExcelExportUtils.exportExcelFile(head,dataMapList);
    }

    /**
     * 导出数据
     * @param head 标题体
     * @param dataList 数据体
     * @return 导出数据的字节
     */
    public byte[] exportDataToExcel(Map<String,String> head,List<T> dataList){
        //排序
        //sorted(dataList);
        String json = JSON.toJSONString(dataList, SerializerFeature.WriteMapNullValue);
        List<Map<String,Object>> dataMapList =
                JSON.parseObject(json,new TypeReference<List<Map<String,Object>>>(){});
        return DynamicEasyExcelExportUtils.exportExcelFile(head,dataMapList);
    }

    /**
     * 将数据排序后，让子类去实现
     * @param dataList 数据
     */
    public void sorted(List<T> dataList){

    }
}
