package com.neu.jianfu.service;

import java.util.List;

import com.neu.jianfu.entity.Cost;
import com.neu.jianfu.page.CostPage;

public interface CostService {
	List<Cost> find();
	void add(Cost cost);
	void delete(Integer id);
	Cost findById(Integer id);
	void update(Cost cost);
	List<Cost> findByPage(CostPage page);
	int findRows();
}
