package tech.icoding.sbc.code.generator.core;

import com.squareup.javapoet.*;
import org.atteo.evo.inflector.English;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Modifier;

/**
 * 用于生成对应的*ConverterClass
 * @author : Joe
 * @date : 2022/4/28
 */
public class ControllerClassBuilder extends  AbstractClassBuilder{



    /**
     * 构建Converter类
     * @param entityClass
     * @param facadeClass
     * @param dataClass
     * @param targetClassName
     * @return
     */
    public TypeSpec buildTypeSpec(Class entityClass,Class dataClass, Class formClass, Class facadeClass,  String targetClassName, String bizName) {

        final String facadeFieldName = getVariableName(facadeClass);

        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(facadeClass, facadeFieldName)
                .addStatement("this.$N = $N", facadeFieldName, facadeFieldName)
                .build();

        final TypeSpec.Builder builder = TypeSpec.classBuilder(targetClassName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(RestController.class).build())
                .addAnnotation(AnnotationSpec.builder(RequestMapping.class).addMember("value", "$S", "/"+English.plural(bizName).toLowerCase()).build())
                .addField(facadeClass, facadeFieldName, Modifier.PRIVATE)
                .addMethod(constructor)
                .addMethod(buildGetMethod(entityClass, dataClass, facadeClass))
                .addMethod(buildFindMethod(entityClass,dataClass, facadeClass))
                .addMethod(buildCreateMethod(formClass,dataClass, facadeClass))
                .addMethod(buildUpdateMethod(entityClass,formClass,dataClass, facadeClass))
                .addMethod(buildDeleteMethod(entityClass, facadeClass));
        return builder.build();
    }

    /**
     * Build get method which will get entity by given id and convert it to data.
     * @param entityClass
     * @param dataClass
     * @param facadeClass
     * @return
     */
    private MethodSpec buildGetMethod(Class entityClass, Class dataClass, Class facadeClass){
        return MethodSpec.methodBuilder("get")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(GetMapping.class).addMember("value", "$S","/{"+ARGS_ID+"}").build())
                .returns(dataClass)
                .addParameter(ParameterSpec.builder(getIdType(entityClass),ARGS_ID,Modifier.FINAL ).addAnnotation(PathVariable.class).build())
                .addStatement("final $T $N = $N.get($N)", dataClass, getVariableName(dataClass),getVariableName(facadeClass), ARGS_ID)
                .addStatement("return $N", getVariableName(dataClass))
                .addJavadoc("Get by ID")
                .build();
    }

//    final PageRequest pageRequest = PageRequest.of(page, size);
//    final Page<CourseEntity> entityPage = courseService.find(pageRequest);
//
//    final List<CourseData> dataList = entityPage.get().map(courseEntity -> {
//        return convert(courseEntity);
//    }).collect(Collectors.toList());


    private MethodSpec buildFindMethod(Class entityClass, Class dataClass, Class facadeClass){

        final ParameterizedTypeName dataPageType = ParameterizedTypeName.get(Page.class, dataClass);
        return MethodSpec.methodBuilder("find")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(GetMapping.class)
                .returns(dataPageType)
//                .addParameter(ParameterSpec.builder(int.class).build())
                .addParameter(int.class, "page").addParameter(int.class, "size")
                .addStatement("return $N.find(page, size)", getVariableName(facadeClass))
                .addJavadoc("Find pageable data")
                .build();
    }

    private MethodSpec buildCreateMethod(Class formClass, Class dataClass, Class facadeClass){

        return MethodSpec.methodBuilder("create")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(PostMapping.class)
                .returns(dataClass)
                .addParameter(ParameterSpec.builder(formClass, getVariableName(formClass),Modifier.FINAL).addAnnotation(RequestBody.class).build())
                .addStatement("return $N.create($N)", getVariableName(facadeClass), getVariableName(formClass))
                .addJavadoc("Create")
                .build();
    }

    private MethodSpec buildUpdateMethod(Class entityClass, Class formClass, Class dataClass, Class facadeClass){

        return MethodSpec.methodBuilder("update")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(PutMapping.class).addMember("value", "$S","/{"+ARGS_ID+"}").build())
                .returns(dataClass)
                .addParameter(ParameterSpec.builder(getIdType(entityClass), ARGS_ID, Modifier.FINAL).addAnnotation(PathVariable.class).build())
                .addParameter(ParameterSpec.builder(formClass, getVariableName(formClass),Modifier.FINAL).addAnnotation(RequestBody.class).build())
                .addStatement("return $N.update($N, $N)", getVariableName(facadeClass),ARGS_ID, getVariableName(formClass))
                .addJavadoc("Create")
                .build();
    }


    private MethodSpec buildDeleteMethod(Class entityClass,  Class facadeClass){
        return MethodSpec.methodBuilder("delete")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(DeleteMapping.class).addMember("value", "$S","/{"+ARGS_ID+"}").build())
                .returns(void.class)
                .addParameter(ParameterSpec.builder(getIdType(entityClass), ARGS_ID, Modifier.FINAL).addAnnotation(PathVariable.class).build())
                .addStatement("$N.delete($N)",getVariableName(facadeClass), ARGS_ID)
//                .addStatement("return $N", getVariableName(dataClass))
                .addJavadoc("Delete by ID")
                .build();
    }

}