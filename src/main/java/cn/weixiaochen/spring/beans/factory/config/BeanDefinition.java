package cn.weixiaochen.spring.beans.factory.config;

/**
 * @author 魏小宸 2021/8/24
 */
public class BeanDefinition {

    public Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return this.bean;
    }
}
