package tech.icoding.sbc.code.generator.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author : Joe
 * @date : 2022/4/29
 */
public class GeneratorUtils {
    public static Type getFirstGenericParameter(Class clazz){
        return ((ParameterizedType)clazz.getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
