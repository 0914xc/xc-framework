package cn.weixiaochen.test;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.support.DefaultBeanFactory;
import cn.weixiaochen.test.controller.IndexController;

/**
 * @author 魏小宸 2021/8/24
 */
public class MySpringApplication {

    public static void main(String[] args) {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(IndexController.class);
        beanFactory.registerBeanDefinition("indexController", beanDefinition);

        IndexController indexController = (IndexController) beanFactory.getBean("indexController");
        indexController.index();
    }
}
