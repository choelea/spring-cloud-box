package tech.icoding.sbc.code.generator.strategy;

import java.io.File;
import java.net.URL;

/**
 * @author : Joe
 * @date : 2022/5/6
 */
public interface IGeneratorStrategy {
    default String getBizName(Class entityClazz){
        final String simpleName = entityClazz.getSimpleName();
        return simpleName;
//        int end = simpleName.indexOf("Entity");
//        return entityClazz.getSimpleName().substring(0, end );
    };
    default String getSrcFolder(Class entityClazz){
        final URL location = entityClazz.getProtectionDomain().getCodeSource().getLocation();
        File classFolder = new File(location.getFile());
        return classFolder.getParentFile().getParent() + "/src/main/java";
    }
    default String getParentPackage(Class entityClazz){
        final String name = entityClazz.getPackage().getName();
        final int end = name.lastIndexOf(".");
        return name.substring(0, end+1) + "Data" ;
    }

    String getPackage(Class entityClass);

    String getSimpleClassName(Class entityClass);
}
