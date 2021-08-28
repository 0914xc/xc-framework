package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.factory.BeanFactory;
import cn.weixiaochen.spring.beans.factory.ListableBeanFactory;
import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.BeansException;

/**
 * @author 魏小宸 2021/8/24
 */
public abstract class AbstractBeanFactory extends DefaultSingletonRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean == null) {
            BeanDefinition beanDefinition = getBeanDefinition(name);
            bean = createBean(name, beanDefinition);
        }
        return bean;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("实例化bean失败" , e);
        }
        registerSingleton(beanName, bean);
        return bean;
    }

}
