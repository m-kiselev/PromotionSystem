package com.mk.web;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Это по сути не view, а инициализатор/холдер ObjectMapper-а для поддержки примесей (для чего все и затевалось)<br>
 * Соответственно запись в выходной поток производится не здесь (как в стандартном view), а <br> 
 * с использованием механизма @ResponseBody / HTTP Message Conversion в CustomizedJacksonHttpMessageConverter <br> 
 */

public class JsonView {
	
	private ObjectMapper	mapper;
	
	/**
	 * Удобный конструктор - Несколько примесей для одного класса
	 * 
	 * @param subject
	 * @param mixinClasses
	 */
	public JsonView(Class<?> subject, Class<?>... mixinClasses) {
	
		this(createMap(subject, mixinClasses));
	}
	
	/**
	 * Основной конструктор
	 * @param mixins
	 */
	public JsonView(Map<Class<?>, Class<?>> mixins) {
	
		mapper = new ObjectMapper();
		if (mixins != null) {
			mapper.setMixInAnnotations(mixins);
		}
		// mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	
	public ObjectMapper getMapper() {
	
		return mapper;
	}
	
	private static Map<Class<?>, Class<?>> createMap(Class<?> subject,
			Class<?>... mixinClasses) {
	
		Map<Class<?>, Class<?>> mixins = new HashMap<Class<?>, Class<?>>();
		for (Class<?> m : mixinClasses) {
			mixins.put(subject, m);
		}
		return mixins;
	}
}