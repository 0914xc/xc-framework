package cn.weixiaochen.spring.beans.factory.config;

import cn.weixiaochen.spring.beans.BeansException;

/**
 * @author 魏小宸 2021/9/11
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
