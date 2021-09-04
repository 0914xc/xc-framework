package cn.weixiaochen.spring.beans.factory.config;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.core.annotation.MergedAnnotations;
import cn.weixiaochen.spring.core.type.AnnotationMetadata;
import cn.weixiaochen.spring.core.type.classreading.MetadataReader;
import cn.weixiaochen.spring.core.type.classreading.SimpleAnnotationMetadata;
import cn.weixiaochen.spring.core.type.classreading.SimpleMetadataReaderFactory;
import cn.weixiaochen.spring.core.util.ClassUtils;

/**
 * @author 魏小宸 2021/8/24
 */
public class BeanDefinition {

    public static String SCOPE_SINGLETON = "singleton";

    public static String SCOPE_PROTOTYPE = "prototype";

    public Object beanClass;

    private String scope = "";

    private AnnotationMetadata annotationMetadata;

    public BeanDefinition(Object beanClass) {
        this.beanClass = beanClass;
        this.annotationMetadata = SimpleAnnotationMetadata.from(getBeanClassName());
    }

    public BeanDefinition(MetadataReader metadataReader) {
        this.annotationMetadata = metadataReader.getAnnotationMetadata();
        setBeanClassName(this.annotationMetadata.getClassName());
    }

    public Class<?> getBeanClass() {
        if (beanClass instanceof String) {
            try {
                this.beanClass = ClassUtils.forName((String) beanClass);
            } catch (ClassNotFoundException e) {
                throw new BeansException(beanClass + "不存在!");
            }
        }
        return (Class<?>) beanClass;
    }

    public void setBeanClass(Object beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        if (beanClass instanceof Class<?>) {
            return ((Class<?>) beanClass).getName();
        }
        return (String) beanClass;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }

    /**
     * Scope为空，默认单例模式
     * @return
     */
    public String getScope() {
        if ("".equals(this.scope)) {
            return SCOPE_SINGLETON;
        }
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public AnnotationMetadata getAnnotationMetadata() {
        return annotationMetadata;
    }

    public void setAnnotationMetadata(AnnotationMetadata annotationMetadata) {
        this.annotationMetadata = annotationMetadata;
    }
}
