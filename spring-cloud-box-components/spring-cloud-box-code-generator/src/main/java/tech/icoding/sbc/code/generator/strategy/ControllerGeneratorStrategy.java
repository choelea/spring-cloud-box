package tech.icoding.sbc.code.generator.strategy;

/**
 * @author : Joe
 * @date : 2022/5/6
 */
public class ControllerGeneratorStrategy implements IGeneratorStrategy{
    @Override
    public String getPackage(Class entityClazz) {
        return getParentPackage(entityClazz) + ".controller" ;
    }

    @Override
    public String getSimpleClassName(Class entityClass) {
        return getParentPackage(entityClass)+"."+getBizName(entityClass)+"Controller";
    }
}
