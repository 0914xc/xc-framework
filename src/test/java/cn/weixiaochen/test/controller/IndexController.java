package cn.weixiaochen.test.controller;

import cn.weixiaochen.spring.core.stereotype.Component;

/**
 * @author 魏小宸 2021/8/27
 */
@Component("index")
public class IndexController {

    public void index() {
        System.out.println("This is a new world!");
    }
}
