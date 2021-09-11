package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.weixiaochen.spring.core.annotation.AnnotationAttributes;
import cn.weixiaochen.spring.core.type.AnnotationMetadata;
import cn.weixiaochen.spring.core.type.classreading.MetadataReaderFactory;
import cn.weixiaochen.spring.core.util.AnnotationUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 魏小宸 2021/9/11
 */
public class ConfigurationClassParser {

    private final MetadataReaderFactory metadataReaderFactory;

    private final BeanDefinitionRegistry registry;

    public ConfigurationClassParser(MetadataReaderFactory metadataReaderFactory, BeanDefinitionRegistry registry) {
        this.metadataReaderFactory = metadataReaderFactory;
        this.registry = registry;
    }

    public void parse(List<BeanDefinition> configCandidates) {
        for (BeanDefinition configCandidate : configCandidates) {
            parseConfigurationClass(configCandidate);
        }
    }

    protected void parseConfigurationClass(BeanDefinition configClass) {
        // 处理@ComponentScan
        AnnotationAttributes attributes = AnnotationUtils.attributesFor(configClass.getAnnotationMetadata(), ComponentScan.class);
        String[] basePackages = attributes.getStringArray("value");
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(this.registry);
        scanner.scan(basePackages);
    }
}
