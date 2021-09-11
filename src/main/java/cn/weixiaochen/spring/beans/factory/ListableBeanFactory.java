package cn.weixiaochen.spring.beans.factory;

import cn.weixiaochen.spring.beans.BeansException;

import java.util.Map;

/**
 * @author 魏小宸 2021/8/28
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回Bean实例
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 按照类型返回Bean的名称
     */
    String[] getBeanNamesForType(Class<?> type);

    /**
     * 返回注册表中所有Bean的名称
     */
    String[] getBeanDefinitionNames();
}
