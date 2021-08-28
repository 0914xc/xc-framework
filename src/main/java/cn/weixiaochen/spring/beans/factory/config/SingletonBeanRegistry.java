package cn.weixiaochen.spring.beans.factory.config;

/**
 * @author 魏小宸 2021/8/28
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);
}
