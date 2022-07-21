package net.sni.jdbc.annotation;

import net.sni.jdbc.controller.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
    Class<? extends Controller<?, ?>> controller();
    Class<?> dto();
}
