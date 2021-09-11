package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author 魏小宸 2021/9/11
 */
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {

    void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException;
}
