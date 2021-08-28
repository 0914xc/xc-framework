package cn.weixiaochen.spring.beans.factory.config;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.beans.util.ClassUtils;

/**
 * @author 魏小宸 2021/8/24
 */
public class BeanDefinition {

    public Object beanClass;

    public BeanDefinition(Object beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        if (beanClass instanceof String) {
            try {
                this.beanClass = ClassUtils.forName((String) beanClass);
            } catch (ClassNotFoundException e) {
                throw new BeansException(beanClass + "不存在!");
            }
        }
        return (Class<?>) beanClass;
    }

    public void setBeanClass(Object beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        if (beanClass instanceof Class<?>) {
            return ((Class<?>) beanClass).getName();
        }
        return (String) beanClass;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }
}
