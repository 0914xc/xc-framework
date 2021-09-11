package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.weixiaochen.spring.beans.factory.support.BeanNameGenerator;
import cn.weixiaochen.spring.core.annotation.ScopeMetadata;

/**
 * @author 魏小宸 2021/8/28
 */
public class AnnotatedBeanDefinitionReader {

    public static final String CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME =
            "cn.weixiaochen.spring.context.annotation.internalConfigurationAnnotationProcessor";

    private BeanDefinitionRegistry registry;

    private BeanNameGenerator beanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
        registerAnnotationConfigProcessor();
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

    /** 注册通用的后置处理器 */
    protected void registerAnnotationConfigProcessor() {
        if (!this.registry.containsBeanDefinition(CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME)) {
            BeanDefinition beanDefinition = new BeanDefinition(ConfigurationClassPostProcessor.class);
            this.registry.registerBeanDefinition(CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME, beanDefinition);
        }
    }
}
