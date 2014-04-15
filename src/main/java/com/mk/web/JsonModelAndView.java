package com.mk.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Это на самом деле только имитация ModelAndView, потому что JsonView - это не view, инициализатор ObjectMapper-а<br>
 * 
 */

public class JsonModelAndView {
	Map<String, Object>		model;
	JsonView				view;
	
	public JsonModelAndView(JsonView view) {
		model = new HashMap<String, Object>();
		this.view = view;
	}
	
	public void set(String key, Object value) {
		model.put(key, value);
	}
	
	public Map<String, Object> getModel() {
		return model;
	}
	
	public JsonView getView() {
		return view;
	}
}
