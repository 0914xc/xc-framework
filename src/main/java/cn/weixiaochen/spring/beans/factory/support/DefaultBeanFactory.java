package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 魏小宸 2021/8/24
 */
public class DefaultBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry {

    protected final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("不存在"+beanName+"的BeanDefinition");
        }
        return beanDefinition;
    }
}
