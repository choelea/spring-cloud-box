package tech.icoding.commons.tools.utils;

import cn.hutool.core.util.RandomUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: GaoDong
 * @Date: 2021/8/16
 * @Description: 批次生成
 */
public class BatchGeneration {

    /**
     * @param name 物资库传w  专业仓传z
     */
    public static String batch(String name){
        if(!"w".equals(name)&&!"z".equals(name)){
            throw new RuntimeException("生成批次异常！");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = dateFormat.format(new Date());

        String random = RandomUtil.randomNumbers(3);

        return name + format + random;

    }

    public static void main(String[] args) {
        System.out.println(batch("z"));
    }
}
