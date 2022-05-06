package tech.icoding.commons.tools.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DynamicEasyExcelExportUtils {
 
    private static final Logger log = LoggerFactory.getLogger(DynamicEasyExcelExportUtils.class);
 
    private static final String DEFAULT_SHEET_NAME = "sheet1"; 
 
    /** 
     * 动态生成导出模版(单表头) 
     * @param headColumns 列名称 
     * @return            excel文件流 
     */ 
    public static byte[] exportTemplateExcelFile(List<String> headColumns){
        List<List<String>> excelHead = Lists.newArrayList();
        headColumns.forEach(columnName -> { excelHead.add(Lists.newArrayList(columnName)); });
        return createExcelFile(excelHead, new ArrayList<>());
    } 
 
    /** 
     * 动态生成模版(复杂表头) 
     * @param excelHead   列名称 
     * @return 字节数据
     */ 
    public static byte[] exportTemplateExcelFileCustomHead(List<List<String>> excelHead){
        return createExcelFile(excelHead, new ArrayList<>());
    } 
 
    /** 
     * 动态导出文件 
     * @param headColumnMap  有序列头部 
     * @param dataList       数据体 
     * @return 字节数据
     */ 
    public static byte[] exportExcelFile(Map<String, String> headColumnMap, List<Map<String, Object>> dataList){
        //获取列名称 
        List<List<String>> excelHead = new ArrayList<>();
        List<List<Object>> excelRows = new ArrayList<>();
        convertList(headColumnMap, dataList, excelHead, excelRows);
        return createExcelFile(excelHead, excelRows);
    }

    /**
     * 导出Excel数据
     * @param excelWriter ExcelWriter对象
     * @param sheetNo 当前sheet的编码
     * @param sheetName sheet名称
     * @param headColumnMap 标题列
     * @param dataList 数据体
     */
    public static void exportExcelData(ExcelWriter excelWriter,
                                       int sheetNo,
                                       String sheetName,
                                       Map<String, String> headColumnMap,
                                       List<Map<String, Object>> dataList){
        //获取列名称
        List<List<String>> excelHead = new ArrayList<>();
        //数据体
        List<List<Object>> excelRows = new ArrayList<>();
        //转换成List数据
        convertList(headColumnMap, dataList, excelHead, excelRows);
        //用户权限标题导入
        WriteSheet mainSheet = EasyExcel.writerSheet(sheetNo, sheetName).head(excelHead).build();
        excelWriter.write(excelRows,mainSheet);

    }

    /**
     * 转换成List数据
     * @param headColumnMap 有序标题
     * @param dataList 数据体
     * @param excelHead 标题列
     * @param excelRows 数据列
     */
    private static void convertList(Map<String, String> headColumnMap,
                                    List<Map<String, Object>> dataList,
                                    List<List<String>> excelHead,
                                    List<List<Object>> excelRows) {
        if (MapUtils.isNotEmpty(headColumnMap)) {
            //key为字段名称，value为标题名称，如果多级列名用逗号隔开
            headColumnMap.forEach((key, value) -> excelHead.add(Lists.newArrayList(value.split(","))));
        }
        if (MapUtils.isNotEmpty(headColumnMap) && CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> dataMap : dataList) {
                List<Object> rows = new ArrayList<>();
                headColumnMap.forEach((key, value) -> {
                    //key为字段名称，value为值。
                    if (dataMap.containsKey(key)) {
                        Object data = dataMap.get(key);
                        rows.add(data);
                    }
                });
                excelRows.add(rows);
            }
        }
    }

    /** 
     * 生成文件 
     * @param excelHead 表头
     * @param excelRows  数据行
     * @return 字节数
     */ 
    private static byte[] createExcelFile(List<List<String>> excelHead, List<List<Object>> excelRows){ 
        try { 
            if(CollectionUtils.isNotEmpty(excelHead)){ 
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                EasyExcel.write(outputStream).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                        .head(excelHead) 
                        .sheet(DEFAULT_SHEET_NAME)
                        .doWrite(excelRows); 
                return outputStream.toByteArray(); 
            } 
        } catch (Exception e) {
            log.error("动态生成excel文件失败，headColumns：" + JSONArray.toJSONString(excelHead) + "，excelRows："
                    + JSONArray.toJSONString(excelRows), e);
        } 
        return null; 
    } 
 
    /** 
     * 导出文件测试 
     * @param args  参数
     * @throws IOException  异常
     */ 
    public static void main(String[] args) throws IOException {
        //导出包含数据内容的文件 
        LinkedHashMap<String, String> headColumnMap = Maps.newLinkedHashMap();
        headColumnMap.put("className","班级"); 
        headColumnMap.put("name","学生信息,姓名"); 
        headColumnMap.put("sex","学生信息,性别"); 
        List<Map<String, Object>> dataList = new ArrayList<>(); 
        for (int i = 0; i < 5; i++) { 
            Map<String, Object> dataMap = Maps.newHashMap(); 
            dataMap.put("className", "一年级"); 
            dataMap.put("name", "张三" + i); 
            dataMap.put("sex", "男"); 
            dataList.add(dataMap); 
        } 
        byte[] stream = exportExcelFile(headColumnMap, dataList); 
        FileOutputStream outputStream = new FileOutputStream(new File("D:/easyexcel-export-user5.xlsx"));
        outputStream.write(stream); 
        outputStream.close(); 
    }
}