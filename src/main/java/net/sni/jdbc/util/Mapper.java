package net.sni.jdbc.util;

import net.sni.jdbc.annotation.Entity;

import java.lang.reflect.InvocationTargetException;

public class Mapper {

    private Mapper() {
    }

    public static Mapper getInstance() {
        return MapperHolder.INSTANCE;
    }

    public static <S> Object toDto(S entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> dtoClazz = entity.getClass().getAnnotation(Entity.class).dto();
        return dtoClazz.getMethod("convertToDto", entity.getClass()).invoke(null, entity);
    }

    public static <S> S toEntity(Object entityDto) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> dtoClazz = entityDto.getClass();
        return (S) dtoClazz.getMethod("convertToEntity", entityDto.getClass()).invoke(null, entityDto);
    }

    private static class MapperHolder {
        private static final Mapper INSTANCE = new Mapper();
    }
}
