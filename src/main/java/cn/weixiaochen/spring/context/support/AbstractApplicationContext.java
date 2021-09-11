package cn.weixiaochen.spring.context.support;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.config.BeanFactoryPostProcessor;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.weixiaochen.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.weixiaochen.spring.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 魏小宸 2021/8/28
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext, BeanDefinitionRegistry {

    private final DefaultListableBeanFactory beanFactory;

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    public AbstractApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    public void refresh() {
        invokeBeanFactoryPostProcessors();
    }

    protected void invokeBeanFactoryPostProcessors() {
        PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return beanFactory.getBeansOfType(type);
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return beanFactory.getBeanNamesForType(type);
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

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanFactory.containsBeanDefinition(beanName);
    }

    @Override
    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {
        this.beanFactoryPostProcessors.add(postProcessor);
    }

    public List<BeanFactoryPostProcessor> getBeanFactoryPostProcessors() {
        return this.beanFactoryPostProcessors;
    }
}
