package cn.weixiaochen.spring.context.support;

import cn.weixiaochen.spring.beans.factory.config.BeanFactoryPostProcessor;
import cn.weixiaochen.spring.beans.factory.config.ConfigurableListableBeanFactory;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.*;

/**
 * @author 魏小宸 2021/9/11
 */
final public class PostProcessorRegistrationDelegate {

    public PostProcessorRegistrationDelegate() {
    }

    public static void invokeBeanFactoryPostProcessors(
            ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {

        if (beanFactory instanceof BeanDefinitionRegistry) {
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

            Map<String, BeanDefinitionRegistryPostProcessor> postProcessors =
                    beanFactory.getBeansOfType(BeanDefinitionRegistryPostProcessor.class);
            invokeBeanDefinitionRegistryPostProcessors(postProcessors.values(), registry);
        }

    }

    private static void invokeBeanDefinitionRegistryPostProcessors(
            Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {
        for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
            postProcessor.postProcessBeanDefinitionRegistry(registry);
        }
    }
}
