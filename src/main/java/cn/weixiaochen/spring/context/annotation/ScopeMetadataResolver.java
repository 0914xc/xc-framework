package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.core.annotation.ScopeMetadata;

/**
 * @author 魏小宸 2021/8/29
 */
public interface ScopeMetadataResolver {

    ScopeMetadata resolveScopeMetadata(BeanDefinition beanDefinition);

}
