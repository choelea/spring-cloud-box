package tech.icoding.commons.tools.excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Excel导出参数
 */
@ApiModel("Excel导出参数设置")
@Data
public class ExcelExportParam {
    /**
     * 标题对象参数，前端传过来的数据，参考：
     * {
     *     userName: 用户名,
     *     age: 年龄
     *     sex: 性别
     * }
     */
    @ApiModelProperty(value = "标题对象参数，前端传过来的数据",example = "{'userName':'用户名','age':'年龄','sex':'性别'}")
    private Map<String,String> fields;
    /**
     * 排序字段 (asc:升序，desc:降序)
     * {
     *     age:asc
     * }
     */
    @ApiModelProperty(value = "排序字段 (asc:升序，desc:降序)",example = "{'age':'asc'}")
    private Map<String,String> sort;

    /**
     * 数据
     */
    List<?> dataList;
}
