package tech.icoding.commons.mybatis.utils;


import tech.icoding.commons.tools.exception.RenException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author gaodong
 * @Description 断言判断类
 */
public class AssertUtils {

    private static final Double ZERO = 0.00d;

    /**
     * 判断 单个是否为空 并自定义返回消息
     * @param object
     * @param msg
     */
    public static void isNull(Object object,String msg){
        if(object instanceof String) {
            if (StringUtils.isBlank((String)object)) {
                throw new RenException(msg);
            }
        }
        else if(object instanceof List) {
           if(((List)object).isEmpty()){
               throw new RenException(msg);
           }
        }
        else if(object == null){
            throw new RenException(msg);
        }
    }

    public static void isZero(Object object){
        isZero("传递id异常",object);
    }

    public static void isZero(String msg,Object object){
        String s = String.valueOf(object);
        try {
            Double v = Double.parseDouble(s);
            if(ZERO.equals(v)){
                throw new RenException(msg);
            }
        }catch (Exception e){
            throw new RenException(msg);
        }
    }

    public static void isZero(String msg,Object... objects){
        for (Object object : objects) {
            isZero(msg,object);
        }
    }
    /**
     * 判断 单个是否为空 默认消息
     * @param object
     */
    public static void isNull(Object object){
        isNull(object,"检验参数为空");
    }
    /**
     * 判断 多个个是否为空 自定义返回消息
     * @param objects
     */
    public static void isNull(String msg, Object... objects){
        for (Object object : objects) {
            isNull(object, msg);
        }
    }
    /**
     * 判断 多个 update insert sql 是否执行成功
     * @param counts
     */
    public static void isSqlSuccess(String msg,int... counts){
        for (int count : counts) {
            isSqlSuccess(count,msg);
        }
    }

    /**
     * 判断 多个 update insert sql 是否执行成功
     * @param counts
     */
    public static void isSqlSuccess(int... counts){
        for (int count : counts) {
            isSqlSuccess(count,"系统异常");
        }
    }

    /**
     * 判断 单个 update insert sql 是否执行成功
     * @param count
     */
    public static void isSqlSuccess(int count){
        isSqlSuccess(count,"系统异常");
    }

    /**
     *
     * @param count 总数
     * @param msg 提示
     */
    private static void isSqlSuccess(int count,String msg){
        if(count<=0){
            throw new RenException(msg);
        }
    }

    /**
     * 判断 update insert sql 是否执行指定数量
     * @param count
     */
    public static void isSqlSuccess(int count,int size){
        if(count<size){
            throw new RenException("系统异常");
        }
    }

    public static void isSqlSuccess(Boolean isSuccess){
        if(isSuccess == false){
            throw new RenException("系统异常");
        }
    }
}
