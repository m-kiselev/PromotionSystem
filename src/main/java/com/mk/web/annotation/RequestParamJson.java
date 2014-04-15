package com.mk.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для json-десериализации указанного параметра запроса в аргумент метода контроллера.
 * Обычно так делает extjs для передачи параметров сортировки и фильтров
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.PARAMETER })
public @interface RequestParamJson {
	/**
	 * @return имя параметра в запросе
	 */
	String value() default "";
	
	/**
	 * @return классы для десериализации. Будут проверены последовательно, возвращен первый удачно десереализованный вариант.
	 * если массив пуст (отсутствует), то класс для десериализации будет взят из аргумента 
	 */
	Class<?>[] classes() default {};
}
