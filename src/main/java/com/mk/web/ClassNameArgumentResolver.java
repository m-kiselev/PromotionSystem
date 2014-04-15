package com.mk.web;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.mk.web.annotation.DefinedFromRequest;


/**
 * Обработчик @DefinedFromRequest параметров RequestMapping-метода контроллера.
 * Упрощенный и измененный ModelAttributeMethodProcessor
 */
public class ClassNameArgumentResolver implements HandlerMethodArgumentResolver {
	
	private static final Logger		log						= LoggerFactory.getLogger(ClassNameArgumentResolver.class);
	
	private static final String		EDIT_REQUEST_PATTERN	= ".*/([a-z]+)/(edit|list|delete|jxls-list)$";
	
	private Pattern					editPattern;
	private Map<String, Class<?>>	mappings;
	
	public ClassNameArgumentResolver() {
	
		this.editPattern = Pattern.compile(EDIT_REQUEST_PATTERN);
	}
	
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		if (parameter.hasParameterAnnotation(DefinedFromRequest.class)) {
			HttpServletRequest req = ((ServletWebRequest) webRequest).getRequest();
			
			Class<?> clazz = defineClassFromRequest(req);
			if (clazz != null) {
				Object attribute = null;
				if (parameter.getParameterType().equals(Class.class)) {
					log.debug("Set '{}' class for '{}' parameter", clazz.getName(), parameter.getParameterName());
					attribute = clazz;
				}
				else {
					attribute = BeanUtils.instantiateClass(clazz);
					log.debug("Instantiated new {} for '{}' parameter", clazz.getName(), parameter.getParameterName());
				}
				
				WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, parameter.getParameterName());
				if (binder.getTarget() != null) {
					((ServletRequestDataBinder) binder).bind(req);
					if (binder.getBindingResult().hasErrors()) {
						throw new BindException(binder.getBindingResult());
					}
				}
				
				// Add resolved attribute and BindingResult at the end of the model
				Map<String, Object> bindingResultModel = binder.getBindingResult().getModel();
				mavContainer.removeAttributes(bindingResultModel);
				mavContainer.addAllAttributes(bindingResultModel);
				return binder.getTarget();
			}
			else{
				log.error("Cannot define class for request '{}'. Expect NPE.",req.getRequestURI());
			}
		}
		return null;
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
	
		return parameter.hasParameterAnnotation(DefinedFromRequest.class);
	}

	/**
	 * Определить класс с которым будет работать метод по веб запросу
	 * 
	 * @param request
	 * @return
	 */
	private Class<?> defineClassFromRequest(HttpServletRequest req) {
	
		Class<?> result = null;
		String uri = req.getRequestURI();
		
		Matcher matcher;
		if ((matcher = editPattern.matcher(uri)).matches()) {
			String entityPath = matcher.group(1);
			result = mappings.get(entityPath);
		}
		
		return result;
	}
	
	public Map<String, Class<?>> getMappings() {
	
		return mappings;
	}
	
	public void setMappings(Map<String, Class<?>> mappings) {
	
		this.mappings = mappings;
	}
	
}
