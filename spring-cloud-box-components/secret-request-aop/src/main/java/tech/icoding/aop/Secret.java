package tech.icoding.aop;
 
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用来标识请求类 或者方法是否使用AOP加密解密
 * @author lxy
 */
@Target({ElementType.TYPE,ElementType.METHOD})              // 可以作用在类上和方法上
@Retention(RetentionPolicy.RUNTIME)                               // 运行时起作用
public @interface Secret {
 
    // 参数类（用来传递加密数据,只有方法参数中有此类或此类的子类才会执行加解密）
    Class<?> value();
 
    // 参数类中传递加密数据的属性名，默认encryptStr
    String encryptStrName() default "encryptStr";
}