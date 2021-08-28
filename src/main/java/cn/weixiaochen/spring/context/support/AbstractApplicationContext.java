package cn.weixiaochen.spring.context.support;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.weixiaochen.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.weixiaochen.spring.context.ApplicationContext;

import java.util.Map;

/**
 * @author 魏小宸 2021/8/28
 */
public abstract class AbstractApplicationContext implements ApplicationContext, BeanDefinitionRegistry {

    private DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    public void refresh() {

    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return beanFactory.getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanFactory.getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return beanFactory.getBean(name);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanFactory.getBeanDefinition(beanName);
    }
}
