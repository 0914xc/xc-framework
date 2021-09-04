package cn.weixiaochen.spring.core.type.classreading;

import cn.weixiaochen.spring.core.annotation.MergedAnnotation;
import cn.weixiaochen.spring.core.annotation.TypeMappedAnnotation;
import cn.weixiaochen.spring.core.asm.SpringAsmInfo;
import cn.weixiaochen.spring.core.util.ClassUtils;
import org.objectweb.asm.AnnotationVisitor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author 魏小宸 2021/8/29
 */
public class MergedAnnotationReadingVisitor<A extends Annotation> extends AnnotationVisitor {

    private final Class<A> annotationType;

    private final Consumer<MergedAnnotation<A>> consumer;

    private final Map<String, Object> attributes = new LinkedHashMap<>(4);

    public MergedAnnotationReadingVisitor(Class<A> annotationType, Consumer<MergedAnnotation<A>> consumer) {
        super(SpringAsmInfo.ASM_VERSION);
        this.annotationType = annotationType;
        this.consumer = consumer;
    }

    @Override
    public void visit(String name, Object value) {
        this.attributes.put(name, value);
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        return new ArrayVisitor(value -> this.attributes.put(name, value));
    }

    @Override
    public void visitEnd() {
        MergedAnnotation<A> mergedAnnotation = new TypeMappedAnnotation<>(this.annotationType, this.attributes);
        this.consumer.accept(mergedAnnotation);
    }

    @SuppressWarnings("unchecked")
    static <A extends Annotation> AnnotationVisitor get(String descriptor, boolean visible,
                                                        Consumer<MergedAnnotation<A>> consumer) {
        if (!visible) {
            return null;
        }
        String typeName = ClassUtils.convertResourcePathToClassName(descriptor.substring(1, descriptor.length()-1));
        try {
            Class<A> annotationType = (Class<A>) ClassUtils.forName(typeName);
            return new MergedAnnotationReadingVisitor<>(annotationType, consumer);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private class ArrayVisitor extends AnnotationVisitor {

        private final List<Object> elements = new ArrayList<>();

        private final Consumer<Object[]> consumer;

        ArrayVisitor(Consumer<Object[]> consumer) {
            super(SpringAsmInfo.ASM_VERSION);
            this.consumer = consumer;
        }

        @Override
        public void visit(String name, Object value) {
            this.elements.add(value);
        }

        @Override
        public void visitEnd() {
            Class<?> componentType = getComponentType();
            Object[] array = (Object[]) Array.newInstance(componentType, this.elements.size());
            this.consumer.accept(this.elements.toArray(array));
        }

        private Class<?> getComponentType() {
            if (this.elements.isEmpty()) {
                return Object.class;
            }
            Object firstElement = this.elements.get(0);
            if (firstElement instanceof Enum) {
                return ((Enum<?>) firstElement).getDeclaringClass();
            }
            return firstElement.getClass();
        }
    }

}

