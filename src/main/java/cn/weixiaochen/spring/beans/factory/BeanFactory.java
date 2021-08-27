package cn.weixiaochen.spring.beans.factory;

import cn.weixiaochen.spring.beans.BeansException;

/**
 * @author 魏小宸 2021/8/24
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;
}
