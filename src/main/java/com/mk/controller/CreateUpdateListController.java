package com.mk.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.web.JsonModelAndView;
import com.mk.dao.ContractDao;
import com.mk.model.Contract;
import com.mk.model.Identifiable;
import com.mk.web.JsonViewRepository;
import com.mk.web.annotation.DefinedFromRequest;


@Controller
public class CreateUpdateListController extends AbstractController {

	@Autowired
	ContractDao						contractDao;

	private static final Logger	log	= LoggerFactory.getLogger(CreateUpdateListController.class);

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(method = RequestMethod.POST, value = "/{entityName:contract|manager|service|headdep|"
			+ "iclient|lclient|monthplan}/delete")
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
			value = "/{entityName:contract|manager|service|headdep|iclient|lclient|monthplan}/edit")
	@ResponseBody
	@Transactional
	public Object createOrUpdate(@DefinedFromRequest Class<?> clazz,
			@DefinedFromRequest Object entity,
			BindingResult bindingResult) throws Exception {
	
		return editOrCreate((Identifiable<Long>) entity, clazz, JsonViewRepository.getViewForEntity(clazz), bindingResult);
	}


	@ResponseBody
	@Transactional(readOnly = true)
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/{entityName:manager|service|contact|headdep|iclient|lclient|monthplan}/list",
			produces = "application/json")
	public Object list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer count,
			@DefinedFromRequest Class<?> clazz) throws IOException {

		return listEntities(clazz, page, count, JsonViewRepository.getViewForList(clazz));
	}

	@ResponseBody
	@Transactional(readOnly = true)
	@RequestMapping(method = RequestMethod.GET, value = "/contracts", produces = "application/json")
	public Object listContracts(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer count,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) Integer managerId) throws IOException, ParseException {

		log.info("managerId = {}", managerId);
		log.info("startDate = {}, endDate = {}", startDate, endDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date sDate = null, eDate = null;
        if (startDate != null && startDate != "") {
        	sDate = sdf.parse(startDate);
        }
        if (endDate != null && endDate != "") {
        	eDate = sdf.parse(endDate);
        }

		Integer from = (page == null) || (count == null) ? null : (page - 1) * count;
		List<Contract> list  = contractDao.listContracts(managerId, sDate, eDate, from, count);
		Long total = contractDao.countContracts(managerId, sDate, eDate);

		JsonModelAndView mav = new JsonModelAndView(jsonViews.getListContractView());
		
		return makeListResponse(mav, list, count, page, total);
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