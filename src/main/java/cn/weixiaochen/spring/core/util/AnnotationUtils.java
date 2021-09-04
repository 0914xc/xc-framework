package cn.weixiaochen.spring.core.util;

import cn.weixiaochen.spring.core.annotation.AnnotationAttributes;
import cn.weixiaochen.spring.core.type.AnnotationMetadata;

/**
 * @author 魏小宸 2021/8/29
 */
public class AnnotationUtils {

    /**
     * 根据注解的类型，获取注解的属性值
     * @param metadata
     * @param annotationType
     * @return
     */
    public static AnnotationAttributes attributesFor(AnnotationMetadata metadata, Class<?> annotationType) {
        return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annotationType));
    }
}
