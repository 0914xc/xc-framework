package cn.weixiaochen.spring.beans.factory.config;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.beans.factory.ListableBeanFactory;

/**
 * @author 魏小宸 2021/9/11
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory {

    void preInstantiateSingletons() throws BeansException;
}
