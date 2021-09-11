package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.beans.BeansException;
import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.config.ConfigurableListableBeanFactory;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import cn.weixiaochen.spring.core.type.AnnotationMetadata;
import cn.weixiaochen.spring.core.type.classreading.MetadataReader;
import cn.weixiaochen.spring.core.type.classreading.MetadataReaderFactory;
import cn.weixiaochen.spring.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理@Configuration类
 * @author 魏小宸 2021/9/11
 */
public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor {

    MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        processConfigBeanDefinitions(registry);
    }

    public void processConfigBeanDefinitions(BeanDefinitionRegistry registry) {
        List<BeanDefinition> configCandidates = new ArrayList<>();
        String[] candidateNames = registry.getBeanDefinitionNames();

        for (String beanName : candidateNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            if (checkConfigurationClassCandidate(beanDefinition)) {
                configCandidates.add(beanDefinition);
            }
        }

        // 没有@Configuration类
        if (configCandidates.isEmpty()) {
            return;
        }

        // 解析每个@Configuration类
        ConfigurationClassParser configurationClassParser =
                new ConfigurationClassParser(this.metadataReaderFactory, registry);
        configurationClassParser.parse(configCandidates);


    }

    public boolean checkConfigurationClassCandidate(BeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();
        try {
            MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(className);
            AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
            Map<String, Object> config = metadata.getAnnotationAttributes(Configuration.class);
            if (config == null) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}