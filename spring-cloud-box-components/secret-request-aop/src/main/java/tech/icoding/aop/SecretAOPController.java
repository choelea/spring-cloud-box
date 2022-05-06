package tech.icoding.aop;

import com.alibaba.fastjson.JSON;
import tech.icoding.util.Sm4Utils;
import tech.icoding.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 切面加密解密抽象类
 * @author lxy
 **/
@Slf4j
public abstract class SecretAOPController<T> {
 
    // 是否进行加密解密，通过配置文件注入（不配置默认为true）
    @Value("${isSecret}")
    boolean isSecret;
    @Value("${secretKey}")
    private String secretKey;
 
    // 定义切点,使用了@Secret注解的类 或 使用了@Secret注解的方法
    @Pointcut("@within(Secret) || @annotation(Secret)")
    public void pointcut(){}

    public abstract void throwException(Throwable throwable);
 
    // 环绕切面
    @Around("pointcut()")
    public T around(ProceedingJoinPoint point){
        T result = null;
        // 获取被代理方法参数
        Object[] args = point.getArgs();
        // 获取被代理对象
        Object target = point.getTarget();
        // 获取通知签名
        MethodSignature signature = (MethodSignature )point.getSignature();
        try {
            // 获取被代理方法
            Method pointMethod = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
            // 获取被代理方法上面的注解@Secret
            Secret secret = pointMethod.getAnnotation(Secret.class);
            // 被代理方法上没有，则说明@Secret注解在被代理类上
            if(secret==null){
                secret = target.getClass().getAnnotation(Secret.class);
            }
            if(secret!=null){
                // 获取注解上声明的加解密类
                Class<?> clazz = secret.value();
                // 获取注解上声明的加密参数名
                String encryptStrName = secret.encryptStrName();
                if(isSecret){
                    for (int i = 0; i < args.length; i++) {
                        // 如果是clazz类型则说明使用了加密字符串encryptStr传递的加密参数
                        if(clazz.isInstance(args[i])){
                            Object cast = clazz.cast(args[i]);      //将args[i]转换为clazz表示的类对象
                            // 通过反射，执行getEncryptStr()方法，获取加密数据
                            Method method = clazz.getMethod(getMethedName(encryptStrName));
                            // 执行方法，获取加密数据
                            String encryptStr = (String) method.invoke(cast);
                            // 加密字符   串是否为空
                            if(StringUtils.isNotBlank(encryptStr)){
                                //解密
                                String json = Sm4Utils.decryptEcb(secretKey,encryptStr);
                                // 转换vo
                               args[i] = JSON.parseObject(json, (Type) args[i].getClass());
                            }
                        }
                        //其他类型，比如基本数据类型、包装类型就不使用加密解密了
                    }
                }
            }
            //执行请求
            result = (T) point.proceed(args);
            if(result != null){
                // 判断配置是否需要返回加密
                if(isSecret){
                    String resultJson = JSON.toJSONString(result);
                    ResultVO resultVO =JSON.parseObject(resultJson,ResultVO.class);
                    // 获取返回值json字符串
                    if(resultVO != null && resultVO.getData() != null){
                        String dataJson = JSON.toJSONString(resultVO.getData());
                        // 加密
                        String s = Sm4Utils.encryptEcb(secretKey,dataJson);
                        resultVO.setData(s);
                        //最后转化成实际返回的对象
                        result = JSON.parseObject(JSON.toJSONString(resultVO), (Type) result.getClass());
                    }
                }
            }

        } catch (NoSuchMethodException e) {
            log.error("@Secret注解指定的类没有字段:encryptStr,或encryptStrName参数字段不存在");
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
//            throw new RuntimeException(throwable.getMessage());
             throwException(throwable);
        }
        return result;
    }
 
    // 转化方法名
    private String getMethedName(String name){
        String first = name.substring(0,1);
        String last = name.substring(1);
        first = StringUtils.upperCase(first);
        return "get" + first + last;
    }
}