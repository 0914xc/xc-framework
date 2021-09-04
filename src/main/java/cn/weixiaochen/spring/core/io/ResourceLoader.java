package cn.weixiaochen.spring.core.io;

/**
 * @author 魏小宸 2021/8/28
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

    Resource getResource(String location);
}
