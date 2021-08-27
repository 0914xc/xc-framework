package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.BeanFactory;
import cn.weixiaochen.spring.beans.BeansException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 魏小宸 2021/8/24
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    protected final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public Object getBean(String name) throws BeansException {
        Object bean = null;
        if (this.beanDefinitionMap.containsKey(name)) {
            BeanDefinition beanDefinition = this.beanDefinitionMap.get(name);
            bean = beanDefinition.getBean();
        }
        return bean;
    }

}
