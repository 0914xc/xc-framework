package cn.weixiaochen.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 魏小宸 2021/8/28
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
