package cn.weixiaochen.spring.core.type.classreading;

import cn.weixiaochen.spring.core.annotation.MergedAnnotation;
import cn.weixiaochen.spring.core.annotation.MergedAnnotations;
import cn.weixiaochen.spring.core.asm.SpringAsmInfo;
import cn.weixiaochen.spring.core.util.ClassUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 魏小宸 2021/8/28
 */
public class SimpleAnnotationMetadataReadingVisitor extends ClassVisitor {

    private String className;

    private SimpleAnnotationMetadata annotationMetadata;

    private List<MergedAnnotation<?>> annotations = new ArrayList<>();

    public SimpleAnnotationMetadataReadingVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = ClassUtils.convertResourcePathToClassName(name);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return MergedAnnotationReadingVisitor.get(descriptor, visible, this.annotations::add);
    }

    @Override
    public void visitEnd() {
        MergedAnnotations mergedAnnotations = new MergedAnnotations(this.annotations);
        this.annotationMetadata = new SimpleAnnotationMetadata(this.className, mergedAnnotations);
    }

    public SimpleAnnotationMetadata getAnnotationMetadata() {
        return annotationMetadata;
    }


}
