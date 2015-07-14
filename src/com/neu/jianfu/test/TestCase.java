package com.neu.jianfu.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neu.jianfu.dao.CostMapper;
import com.neu.jianfu.entity.Cost;
import com.neu.jianfu.page.CostPage;

public class TestCase {

		@Test
		public void test1(){
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			CostMapper m = ctx.getBean(CostMapper.class);
			CostPage page = new CostPage();
			page.setCurrentPage(2);
			List<Cost> list = m.findByPage(page);
			for (Cost c : list){
				System.out.println(c.getName());
			}
		}
}
