package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.weixiaochen.spring.beans.factory.support.BeanNameGenerator;
import cn.weixiaochen.spring.core.annotation.ScopeMetadata;

/**
 * @author 魏小宸 2021/8/28
 */
public class AnnotatedBeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    private BeanNameGenerator beanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void register(Class<?>... componentClasses) {
        for (Class<?> componentClass : componentClasses) {
            registerBean(componentClass);
        }
    }

    protected void registerBean(Class<?> beanClass) {
        BeanDefinition beanDefinition = new BeanDefinition(beanClass);
        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(beanDefinition);
        beanDefinition.setScope(scopeMetadata.getScopeName());
        String beanName = beanNameGenerator.generateBeanName(beanDefinition);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
