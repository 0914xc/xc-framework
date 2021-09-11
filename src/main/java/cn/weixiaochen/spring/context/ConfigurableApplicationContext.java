package cn.weixiaochen.spring.context;

import cn.weixiaochen.spring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author 魏小宸 2021/9/11
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

}
