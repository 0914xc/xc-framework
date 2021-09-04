package cn.weixiaochen.spring.core.type.classreading;

import cn.weixiaochen.spring.core.annotation.MergedAnnotation;
import cn.weixiaochen.spring.core.annotation.MergedAnnotations;
import cn.weixiaochen.spring.core.type.AnnotationMetadata;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author 魏小宸 2021/8/28
 */
public class SimpleAnnotationMetadata implements AnnotationMetadata {

    private String className;

    private MergedAnnotations annotations;

    public SimpleAnnotationMetadata(String className, MergedAnnotations annotations) {
        this.className = className;
        this.annotations = annotations;
    }

    @Override
    public Map<String, Object> getAnnotationAttributes(Class<?> annotationType) {
        for (MergedAnnotation<Annotation> annotation : annotations) {
            if (annotationType.isAssignableFrom(annotation.getType())) {
                return annotation.getAnnotationAttributes();
            }
        }
        return null;
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    public static AnnotationMetadata from(String className) {
        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = null;
        try {
            metadataReader = metadataReaderFactory.getMetadataReader(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metadataReader.getAnnotationMetadata();
    }
}
