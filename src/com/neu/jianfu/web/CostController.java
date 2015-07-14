package com.neu.jianfu.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.jianfu.entity.Cost;
import com.neu.jianfu.page.CostPage;
import com.neu.jianfu.service.CostService;


@Controller
@RequestMapping("/cost")
public class CostController implements Serializable {

	@Resource
	private CostService costService;
	
	@RequestMapping("/find.do")
	public String find(Model model,CostPage page){
		// Ã»Ð´Íê°¡¡£¡£
		List<Cost> list = costService.findByPage(page);
		
		model.addAttribute("cost",list);
		model.addAttribute("CostPage", page);
		return "main/cost_list";
	}
	
	@RequestMapping("/toAddCost.do")
	public String toaddCost(){
		return "main/cost_add";
	}
	
	@RequestMapping("/addCost.do")
	public String addCost(Cost cost){
		costService.add(cost);
		return "redirect:find.do";
	}
	
	@RequestMapping("/deleteCost.do")
	public String deleteCost(Integer id){
		costService.delete(id);
		return "redirect:find.do";
	}
	
	@RequestMapping("/toUpdateCost.do")
	public String updateCost(){
		// TODO Auto-generated method stub
		//.findById(id)
		return "main/cost_update";
	}
	
}
