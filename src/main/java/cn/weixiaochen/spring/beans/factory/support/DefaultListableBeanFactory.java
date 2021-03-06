package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.*;

/**
 * @author 魏小宸 2021/8/24
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {

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

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach(((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                T bean = (T)getBean(beanName);
                result.put(beanName, bean);
            }
        }));
        return result;
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return getBeansOfType(type).keySet().toArray(new String[0]);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[0]);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {

    }
}
