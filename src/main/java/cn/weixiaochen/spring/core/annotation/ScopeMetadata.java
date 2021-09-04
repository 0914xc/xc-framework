package cn.weixiaochen.spring.core.annotation;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;

/**
 * @author 魏小宸 2021/8/29
 */
public class ScopeMetadata {

    private String scopeName = BeanDefinition.SCOPE_SINGLETON;

    public String getScopeName() {
        return this.scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}
