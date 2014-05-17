package com.mk.dao;

import java.util.Date;
import java.util.List;

import com.mk.model.Contract;

public interface ContractDao {

	List<Contract> listContracts(Integer managerId, Date startDate, Date endDate, Integer from, Integer count);
	Long countContracts(Integer managerId, Date startDate, Date endDate);
}
