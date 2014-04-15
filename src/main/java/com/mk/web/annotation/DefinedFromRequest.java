package com.mk.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Маркирует параметр RequestMapping-метода контроллера.
 * Если отмеченный параметр имеет тип Class, то ему будет назначен определенный из запроса класс.
 * Иначе, параметр будет инициализирован экземпляром объекта определенного из запроса класса .
 */
@Target(value = { ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DefinedFromRequest {
	
}
