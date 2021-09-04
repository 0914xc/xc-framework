package cn.weixiaochen.spring.core.annotation;

import cn.weixiaochen.spring.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Iterator;
import java.util.List;

/**
 * @author 魏小宸 2021/8/29
 */
public class MergedAnnotations implements Iterable<MergedAnnotation<Annotation>> {

    MergedAnnotation<?>[] annotations;

    public MergedAnnotations(List<MergedAnnotation<?>> annotations) {
       this.annotations = annotations.toArray(new MergedAnnotation<?>[0]);
    }

    @Override
    public Iterator<MergedAnnotation<Annotation>> iterator() {
        return new AnnotationIterator<>();
    }

    private class AnnotationIterator<A extends Annotation> implements Iterator<MergedAnnotation<A>> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < MergedAnnotations.this.annotations.length;
        }

        @Override
        @SuppressWarnings("unchecked")
        public MergedAnnotation<A> next() {
            return (MergedAnnotation<A>) MergedAnnotations.this.annotations[cursor++];
        }
    }
}
