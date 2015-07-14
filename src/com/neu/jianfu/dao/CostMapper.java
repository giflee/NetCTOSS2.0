package com.neu.jianfu.dao;

import java.util.List;

import com.neu.jianfu.annotation.Myannotation;
import com.neu.jianfu.entity.Cost;
import com.neu.jianfu.page.CostPage;
@Myannotation
public interface CostMapper {
	List<Cost> findAll();
	List<Cost> findByPage(CostPage page);
	void add(Cost cost);
	void delete(Integer id);
	Cost findById(Integer id);
	void update(Cost cost);
	int findRows();
}
