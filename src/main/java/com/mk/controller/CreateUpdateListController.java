package com.mk.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.model.Identifiable;
import com.mk.web.JsonViewRepository;
import com.mk.web.annotation.DefinedFromRequest;


@Controller
public class CreateUpdateListController extends AbstractController {

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(CreateUpdateListController.class);

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(method = RequestMethod.POST, value = "/{entityName:contract|manager|service}/delete")
	@ResponseBody
	@Transactional
	public Object delete(@DefinedFromRequest Object entity) {
		abstractDao.deleteObject(entity.getClass(), ((Identifiable<? extends Serializable>) entity).getId());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("status", "success");
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/{entityName:contract|manager|service}/edit")
	@ResponseBody
	@Transactional
	public Object createOrUpdate(@DefinedFromRequest Class<?> clazz,
			@DefinedFromRequest Object entity,
			BindingResult bindingResult) throws Exception {
	
		return editOrCreate((Identifiable<Long>) entity, clazz, JsonViewRepository.getViewForEntity(clazz), bindingResult);
	}

	@RequestMapping(
			method = RequestMethod.GET,
			value = "/{entityName:contract|manager|service|contact}/list",
			produces = "application/json")
	@ResponseBody
	@Transactional(readOnly = true)
	public Object list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer count,
			@DefinedFromRequest Class<?> clazz) throws IOException {

		System.out.println(clazz.getName());
		return listEntities(clazz, page, count, JsonViewRepository.getViewForList(clazz));
	}
	
	/**
	 * Экспорт в xls с помощью JxlsView <br>
	 * Шаблон для экспорта будет определен внутри JxlsView с помощью значения entityName <br>
	 */
//	@RequestMapping(method = RequestMethod.GET, value = "/{entityName:csimple|contenttype|performer|textauthor|"
//			+ "musicauthor|tag|opc|currency|territory|company|user|windowcase}/jxls-list")
//	public ModelAndView listJxls(HttpServletRequest request,
//			@RequestParam(value = "page", required = false) Integer page,
//			@RequestParam(value = "limit", required = false) Integer count,
//			@DefinedFromRequest Class<?> clazz,
//			@ModelAttribute @PathVariable String entityName) throws IOException {
//
//		return new ModelAndView("jxlsView", listEntities(clazz, page, count));
//	}
}