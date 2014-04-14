package com.mk.dao;

import java.util.List;

import com.mk.model.Manager;

public interface ManagerDao {

	void saveOrUpdate(Manager manager);
	List<Manager> getManagers();
	void delete(Manager manager);
}
