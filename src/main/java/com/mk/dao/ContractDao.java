package com.mk.dao;

import java.util.List;

import com.mk.model.Contract;

public interface ContractDao {

	void saveOrUpdate(Contract contract);
	List<Contract> getContracts();
	void delete(Contract contract);
}
