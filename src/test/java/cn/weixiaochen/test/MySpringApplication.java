package cn.weixiaochen.test;

import cn.weixiaochen.spring.context.annotation.AnnotationConfigApplicationContext;
import cn.weixiaochen.test.controller.IndexController;

/**
 * @author 魏小宸 2021/8/24
 */
public class MySpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(IndexController.class);
        IndexController indexController = (IndexController) applicationContext.getBean("indexController");
        indexController.index();
    }
}
