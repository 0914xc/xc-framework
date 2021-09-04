package cn.weixiaochen.spring.core.annotation;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author 魏小宸 2021/8/29
 */
public class MissingMergedAnnotation<A extends Annotation> extends AbstractMergedAnnotation<A> {

    @Override
    public Class<A> getType() {
        return null;
    }

    @Override
    public Map<String, Object> getAnnotationAttributes() {
        return null;
    }


}
