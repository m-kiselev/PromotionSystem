package com.mk.web;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mk.web.annotation.RequestParamJson;

/**
 * Ресолвит @RequestParamJson аргумент метода контроллера.
 */
public class SingleJsonArgumentResolver implements HandlerMethodArgumentResolver {
	
	private ObjectMapper	mapper	= new ObjectMapper();
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
	
		return parameter.hasParameterAnnotation(RequestParamJson.class);
	}
	
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
	
		Object result = null;
		RequestParamJson ann = parameter.getParameterAnnotation(RequestParamJson.class);
		if (ann != null) {
			String requestParamName = ann.value();
			/* имя параметра: сперва берется из аннотации, если там пусто, то имя аргумента */
			requestParamName = requestParamName.isEmpty() ? parameter.getParameterName() : requestParamName;
			String requestParamValue = webRequest.getParameter(requestParamName);
			if (requestParamValue != null) {
				/* значение будет десериализовано только если оно присутствует в запросе */
				Class<?>[] classesToTry = ArrayUtils.add(ann.classes(), parameter.getParameterType());
				result = deserialize(requestParamValue, classesToTry);
			}
		}
		return result;
	}
	
	/**
	 * json-Десериализация в указанные классы. Возвращается 1й успешно десериализованный объект.
	 *  
	 * @param json
	 * @param classesToTry
	 * @return
	 */
	private Object deserialize(String json,
			Class<?>... classesToTry) {
		
		Object result = null;
		for (Class<?> c : classesToTry) {
			try {
				result = mapper.readValue(json, c);
				break;
			}
			catch (Exception e) {
			}
		}
		return result;
	}
}
