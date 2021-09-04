package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.core.annotation.AnnotationAttributes;
import cn.weixiaochen.spring.core.annotation.ScopeMetadata;
import cn.weixiaochen.spring.core.util.AnnotationUtils;

import java.lang.annotation.Annotation;

/**
 * @author 魏小宸 2021/8/29
 */
public class AnnotationScopeMetadataResolver implements ScopeMetadataResolver {

    protected Class<? extends Annotation> scopeAnnotationType = Scope.class;

    @Override
    public ScopeMetadata resolveScopeMetadata(BeanDefinition beanDefinition) {
        ScopeMetadata scopeMetadata = new ScopeMetadata();
        AnnotationAttributes attributes = AnnotationUtils.attributesFor(
                beanDefinition.getAnnotationMetadata(), this.scopeAnnotationType);
        if (attributes != null) {
            scopeMetadata.setScopeName(attributes.getString("value"));
        }
        return scopeMetadata;
    }
}
