package com.neu.jianfu.service;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neu.jianfu.dao.CostMapper;
import com.neu.jianfu.entity.Cost;
import com.neu.jianfu.page.CostPage;
@Service
public class CostServiceImpl implements Serializable, CostService {

	@Resource
	private CostMapper costDao;
	
	public List<Cost> find() {
		List<Cost> list = new ArrayList<Cost>();
		list = costDao.findAll();
		return list;
	}

	public void add(Cost cost) {
		cost.setCreatime(new Timestamp(System.currentTimeMillis()));
		costDao.add(cost);
	}
	
	public void delete(Integer id){
		costDao.delete(id);
	}

	public Cost findById(Integer id) {
		Cost cost = new Cost();
		cost = costDao.findById(id);
		return cost;
	}

	public void update(Cost cost) {
		// TODO Auto-generated method stub
		
	}

	public List<Cost> findByPage(CostPage page) {
		List<Cost> list = new ArrayList<Cost>();
		int row = costDao.findRows();
		page.setRow(row);
		list = costDao.findByPage(page);
		return list;
	}

	public int findRows() {
		
		return costDao.findRows();
	}


}
