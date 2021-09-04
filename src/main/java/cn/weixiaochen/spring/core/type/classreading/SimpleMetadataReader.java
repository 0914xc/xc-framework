package cn.weixiaochen.spring.core.type.classreading;

import cn.weixiaochen.spring.core.io.Resource;
import cn.weixiaochen.spring.core.type.AnnotationMetadata;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 魏小宸 2021/8/28
 */
public class SimpleMetadataReader implements MetadataReader {

    private static final int PARSING_OPTIONS = ClassReader.SKIP_DEBUG
            | ClassReader.SKIP_CODE | ClassReader.SKIP_FRAMES;

    private final AnnotationMetadata annotationMetadata;

    public SimpleMetadataReader(Resource resource) throws IOException {
        SimpleAnnotationMetadataReadingVisitor visitor = new SimpleAnnotationMetadataReadingVisitor();
        getClassReader(resource).accept(visitor, PARSING_OPTIONS);
        this.annotationMetadata = visitor.getAnnotationMetadata();
    }

    private static ClassReader getClassReader(Resource resource) throws IOException {
        try (InputStream is = resource.getInputStream()) {
            try {
                return new ClassReader(is);
            } catch (IllegalArgumentException ex) {
                throw new IOException();
            }
        }
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
