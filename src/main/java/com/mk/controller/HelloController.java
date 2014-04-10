package com.mk.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mk.dao.TestDao;
import com.mk.model.Contact;

@Controller
public class HelloController {

	@Autowired
	TestDao		testDao;

	@RequestMapping(method = RequestMethod.GET,  value = "/welcome")
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		
		System.out.println("================");
		
		List<Contact> contactList = testDao.getContacts();
		
		for (Contact contact : contactList) {
			System.out.println("Id: " + contact.getId() + " | Name:"  + contact.getName() + " | Email:" + contact.getEmail());
		}
		
		System.out.println("================");
		
		return "hello";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/start")
	public String start(ModelMap model) {

		return "index";
	}
	

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/contacts")
	public List<Contact> getClients() throws Exception {
		List<Contact> list =  testDao.getContacts();
		return list;
	}
	
}