package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;

/**
 * @author 魏小宸 2021/8/24
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

}
