package cn.weixiaochen.spring.core.type;

import java.util.Map;

/**
 * @author 魏小宸 2021/8/28
 */
public interface AnnotatedTypeMetadata {

    Map<String, Object> getAnnotationAttributes(Class<?> annotationType);
}
