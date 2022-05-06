package tech.icoding.sbc.code.generator.core;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import lombok.Data;
import tech.icoding.scb.core.data.BaseData;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 用于生成对应的*DataClass
 * @author : Joe
 * @date : 2022/4/28
 */
public class DataClassBuilder extends AbstractClassBuilder{

    public TypeSpec buildTypeSpec(Class entityClass, String targetClassName) {
        final Type firstGenericParameter = GeneratorUtils.getFirstGenericParameter(entityClass);

        Class sourceClass = entityClass;
        final TypeSpec.Builder builder = TypeSpec.classBuilder(targetClassName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Data.class);

        final Field[] declaredFields = sourceClass.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            builder.addField(declaredFields[i].getGenericType(),declaredFields[i].getName(), Modifier.PRIVATE);
        }
        return builder.build();
    }
}
