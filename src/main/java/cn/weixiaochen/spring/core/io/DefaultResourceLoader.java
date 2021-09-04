package cn.weixiaochen.spring.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 魏小宸 2021/8/28
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        // classpath下的资源
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        // 先当成URL来处理，如果抛出异常就当成FileSystem来处理
        try {
            URL url = new URL(location);
            return new UrlResource(url);
        } catch (MalformedURLException ex) {
            return new FileSystemResource(location);
        }
    }
}
