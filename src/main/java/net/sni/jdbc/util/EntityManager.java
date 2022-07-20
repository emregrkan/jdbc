package net.sni.jdbc.util;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class EntityManager {
    public static void initAnnotatedEntities(final Class<? extends Annotation> annotation) {

        new Reflections("net.sni.jdbc.dao").getTypesAnnotatedWith(annotation).forEach(clazz -> {
            try {
                net.sni.jdbc.dao.Dao<?, ?> dao = (net.sni.jdbc.dao.Dao<?, ?>) clazz.getMethod("getInstance").invoke(null);
                dao.init();
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
