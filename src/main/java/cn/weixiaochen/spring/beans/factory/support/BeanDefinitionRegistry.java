package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;

/**
 * @author 魏小宸 2021/8/24
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();

}
