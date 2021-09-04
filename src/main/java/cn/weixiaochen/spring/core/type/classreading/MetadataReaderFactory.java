package cn.weixiaochen.spring.core.type.classreading;

import cn.weixiaochen.spring.core.io.Resource;

import java.io.IOException;

/**
 * @author 魏小宸 2021/9/4
 */
public interface MetadataReaderFactory {

    MetadataReader getMetadataReader(String className) throws IOException;

    MetadataReader getMetadataReader(Resource resource) throws IOException;
}
