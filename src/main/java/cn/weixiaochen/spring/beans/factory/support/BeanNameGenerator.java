package cn.weixiaochen.spring.beans.factory.support;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;

/**
 * @author 魏小宸 2021/8/28
 */
public interface BeanNameGenerator {

    String generateBeanName(BeanDefinition beanDefinition);
}
