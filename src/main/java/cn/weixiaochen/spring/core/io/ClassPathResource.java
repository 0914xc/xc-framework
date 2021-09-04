package cn.weixiaochen.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 魏小宸 2021/8/28
 */
public class ClassPathResource implements Resource {

    private final String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
         return this.getClass().getClassLoader().getResourceAsStream(path);
    }
}
