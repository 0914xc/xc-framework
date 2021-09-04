package cn.weixiaochen.spring.core.annotation;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author 魏小宸 2021/8/29
 */
public interface MergedAnnotation<A extends Annotation> {

    String VALUE = "value";

    Class<A> getType();

    Map<String, Object> getAnnotationAttributes();
}
