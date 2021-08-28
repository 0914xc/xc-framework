package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.support.BeanNameGenerator;
import cn.weixiaochen.spring.core.util.ClassUtils;

/**
 * @author 魏小宸 2021/8/28
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    public static final AnnotationBeanNameGenerator INSTANCE = new AnnotationBeanNameGenerator();

    @Override
    public String generateBeanName(BeanDefinition beanDefinition) {
        String beanClassName = beanDefinition.getBeanClassName();
        return ClassUtils.getShortName(beanClassName);
    }

}
