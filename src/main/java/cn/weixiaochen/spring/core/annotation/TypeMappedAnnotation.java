package cn.weixiaochen.spring.core.annotation;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author 魏小宸 2021/8/29
 */
public class TypeMappedAnnotation<A extends Annotation> implements MergedAnnotation<A> {

    /* 根注解 */
    private TypeMappedAnnotation root;

    /* 上一级注解 */
    private TypeMappedAnnotation source;

    /* 注解类型 */
    private final Class<A> annotationType;

    /* 注解属性 */
    private final Map<String, Object> attributes;

    public TypeMappedAnnotation(Class<A> annotationType, Map<String, Object> attributes) {
        this.annotationType = annotationType;
        this.attributes = attributes;
    }

    @Override
    public Class<A> getType() {
        return annotationType;
    }

    @Override
    public Map<String, Object> getAnnotationAttributes() {
        return this.attributes;
    }
}
