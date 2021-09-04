package cn.weixiaochen.spring.context.annotation;

import cn.weixiaochen.spring.beans.factory.config.BeanDefinition;
import cn.weixiaochen.spring.beans.factory.support.BeanDefinitionRegistry;
import cn.weixiaochen.spring.beans.factory.support.BeanNameGenerator;
import cn.weixiaochen.spring.core.annotation.ScopeMetadata;
import cn.weixiaochen.spring.core.io.FileSystemResource;
import cn.weixiaochen.spring.core.io.Resource;
import cn.weixiaochen.spring.core.type.classreading.MetadataReader;
import cn.weixiaochen.spring.core.type.classreading.MetadataReaderFactory;
import cn.weixiaochen.spring.core.type.classreading.SimpleMetadataReader;
import cn.weixiaochen.spring.core.type.classreading.SimpleMetadataReaderFactory;
import cn.weixiaochen.spring.core.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 扫描类路径，完成对BeanDefinition的注册
 * @author 魏小宸 2021/8/28
 */
public class ClassPathBeanDefinitionScanner {

    private BeanDefinitionRegistry registry;

    private BeanNameGenerator beanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;

    private MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public Set<BeanDefinition> scan(String... basePackages) {
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(candidate);
                candidate.setScope(scopeMetadata.getScopeName());
                String beanName = this.beanNameGenerator.generateBeanName(candidate);
                beanDefinitions.add(candidate);
                this.registry.registerBeanDefinition(beanName, candidate);
            }
        }
        return beanDefinitions;
    }

    protected Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        String packageSearchPath = basePackage.replace(ClassUtils.PACKAGE_SEPARATOR, ClassUtils.PATH_SEPARATOR);
        // 获取包路径下的所有class文件
        Set<Resource> resources = findPathMatchingResources(packageSearchPath);
        for (Resource resource : resources) {
            try {
                // 通过字节码（asm）获取BeanDefinition
                MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                BeanDefinition beanDefinition = new BeanDefinition(metadataReader);
                if (isCandidateComponent(beanDefinition)) {
                    candidates.add(beanDefinition);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return candidates;
    }

    private Set<Resource> findPathMatchingResources(String path) {
        URL rooDirUrl = ClassUtils.getDefaultClassLoader().getResource(path);
        if (rooDirUrl == null) {
            return Collections.emptySet();
        } else {
            File rootDir = new File(rooDirUrl.getFile());
            Set<File> matchingFiles = new LinkedHashSet<>();
            retrieveMatchingFiles(rootDir, matchingFiles);
            Set<Resource> resources = new LinkedHashSet<>(matchingFiles.size());
            for (File matchingFile : matchingFiles) {
                resources.add(new FileSystemResource(matchingFile.getPath()));
            }
            return resources;
        }
    }

    /**
     * 递归遍历文件，获取包路径下的所有class文件
     * @param rootDir 跟路径
     * @param matchingFiles 匹配的class文件
     */
    protected void retrieveMatchingFiles(File rootDir, Set<File> matchingFiles) {
        File[] files = rootDir.listFiles();
        if (files == null) {
            return;
        }
        for (File content : files) {
            if (content.isDirectory()) {
                retrieveMatchingFiles(content, matchingFiles);
            } else {
                if (content.getName().endsWith(".class")) {
                    matchingFiles.add(content);
                }
            }
        }
    }

    protected boolean isCandidateComponent(BeanDefinition beanDefinition) {
        // todo 筛选满足条件的BeanDefinition
        return true;
    }

}
