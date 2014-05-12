package com.mk.dao;

import java.util.Date;
import java.util.List;

import com.mk.model.Contract;

public interface ContractDao {

	List<Contract> listContracts(Long managerId, Date startDate, Date endDate, Integer from, Integer count);
	Long countContracts(Long managerId, Date startDate, Date endDate);
}
