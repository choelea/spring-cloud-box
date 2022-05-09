package tech.icoding.sbc.code.generator.core;

import com.squareup.javapoet.TypeSpec;
import lombok.Data;

import javax.lang.model.element.Modifier;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 用于生成对应的*DataClass
 * @author : Joe
 * @date : 2022/4/28
 */
public class FormClassBuilder extends AbstractClassBuilder{

    public TypeSpec buildTypeSpec(Class entityClass, String targetClassName) {
         final TypeSpec.Builder builder = TypeSpec.classBuilder(targetClassName)
                .addModifiers(Modifier.PUBLIC)
                 .addSuperinterface(Serializable.class)
                .addAnnotation(Data.class);

        final Field[] declaredFields = entityClass.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            builder.addField(declaredFields[i].getGenericType(),declaredFields[i].getName(), Modifier.PRIVATE);
        }
        return builder.build();
    }
}
