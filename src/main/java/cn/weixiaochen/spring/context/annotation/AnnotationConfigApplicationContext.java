package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.context.support.AbstractApplicationContext;

/**
 * 主要用于加载配置类
 * @author 魏小宸 2021/8/28
 */
public class AnnotationConfigApplicationContext extends AbstractApplicationContext {

    AnnotatedBeanDefinitionReader reader;

    public AnnotationConfigApplicationContext() {
        this.reader = new AnnotatedBeanDefinitionReader(this);
    }

    public AnnotationConfigApplicationContext(Class<?>... componentClasses) {
        this();
        register(componentClasses);
        refresh();
    }

    private void register(Class<?>... componentClasses) {
        reader.register(componentClasses);
    }
}
