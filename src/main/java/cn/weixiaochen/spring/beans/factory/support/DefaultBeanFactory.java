package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;

/**
 * @author 魏小宸 2021/8/24
 */
public class DefaultBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry {

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
    }
}
