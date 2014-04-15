package com.mk.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.mk.dao.AbstractDao;
import com.mk.model.Identifiable;
import com.mk.web.JsonModelAndView;
import com.mk.web.JsonView;

public class AbstractController {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	protected AbstractDao			abstractDao;
	
	@Autowired
	protected SessionFactory		sessionFactory;
	
	/**
	 * Standart "edit or create" method. Use only with Long Identifiable entities
	 * 
	 * @param object
	 * @param clazz
	 * @param bindingResult
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Object editOrCreate(Identifiable<Long> object,
			Class<?> clazz,
			JsonView view,
			BindingResult bindingResult) throws Exception {
	
		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.toString());
		}
		else {
			if (object.getId() != null && object.getId() > 0) {
				abstractDao.update(object);
			}
			else {
				abstractDao.save(object);
			}
		}
		Long id = object.getId();
		sessionFactory.getCurrentSession().evict(object);
		object = (Identifiable<Long>) clazz.cast(abstractDao.getById(clazz, id));
		
		List list = new ArrayList();
		list.add(object);
		return makeListResponse(new JsonModelAndView(view), list, null, null, null);
	}
	
	/**
	 * Создает новый JsonModelAndView, выполняет запрос к БД, и заполняет JsonModelAndView списком полученных сущностей
	 * 
	 * @param clazz класс сущности
	 * @param page номер страницы
	 * @param count количество на странице
	 * @param filters список фильтров
	 * @param orderList список порядков
	 * @param view JsonView для создания JsonModelAndView
	 * @return созданный объект JsonModelAndView
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected <T> Object listEntities(Class<T> clazz,
			Integer page,
			Integer count,
			JsonView view) throws IOException {
	
		JsonModelAndView mav = new JsonModelAndView(view);
		
		Integer from = (page == null) || (count == null) ? null : (page - 1) * count;
		List<T> list = abstractDao.list(clazz, from, count);

		return makeListResponse(mav, list, count, page, abstractDao.count(clazz));
	}

	/**
	 * Создает новый Model, выполняет запрос к БД, и заполняет ее списком полученных сущностей
	 * 
	 * @param clazz класс сущности
	 * @param page номер страницы
	 * @param count количество на странице
	 * @param filters список фильтров
	 * @param orderList список порядков
	 * @return созданный Model
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected <T> Map<String, Object> listEntities(Class<T> clazz,
			Integer page,
			Integer count) throws IOException {
	
		Map<String, Object> model = new HashMap<String, Object>();
		
		Integer from = (page == null) || (count == null) ? null : (page - 1) * count;
		List<T> list = abstractDao.list(clazz, from, count);
		
		makeListResponse(model, list, count, page, abstractDao.count(clazz));
		return model;
	}	
	
	/*TODO метод требует удаления при рефакторинге*/
	protected Object makeListResponse(JsonModelAndView mav,
			List<?> list,
			Integer count,
			Integer page,
			Long total) {
	
		makeListResponse(mav.getModel(), list, count, page, total);
		return mav.getModel();
	}
	
	protected void makeListResponse(Map<String,Object> model,
			List<?> list,
			Integer count,
			Integer page,
			Long total) {
	
		if (list == null) {
			model.put("success", false);
			model.put("error", "Returned list is null");
		}
		else {
			model.put("success", true);
			model.put("total", total);
			model.put("itemsOnPage", count);
			model.put("currentPage", page);
			model.put("totalPages", ((count == null) || (total == null)) ? 1 : (int) (Math.floor(total / count) + 1));
			model.put("entities", list);
		}
	}	
}
