package cn.weixiaochen.spring.core.type.classreading;

import cn.weixiaochen.spring.core.io.DefaultResourceLoader;
import cn.weixiaochen.spring.core.io.Resource;
import cn.weixiaochen.spring.core.io.ResourceLoader;
import cn.weixiaochen.spring.core.util.ClassUtils;

import java.io.IOException;

/**
 * @author 魏小宸 2021/9/4
 */
public class SimpleMetadataReaderFactory implements MetadataReaderFactory {

    private final ResourceLoader resourceLoader;

    public SimpleMetadataReaderFactory() {
        this.resourceLoader = new DefaultResourceLoader();
    }

    @Override
    public MetadataReader getMetadataReader(String className) throws IOException {
            String resourcePath = ResourceLoader.CLASSPATH_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(className) + ClassUtils.CLASS_FILE_SUFFIX;
            Resource resource = this.resourceLoader.getResource(resourcePath);
            return getMetadataReader(resource);
    }

    @Override
    public MetadataReader getMetadataReader(Resource resource) throws IOException {
        return new SimpleMetadataReader(resource);
    }
}
