package com.easybuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easybuy.pojo.EasyUIDataGridResult;
import com.easybuy.service.ItemService;

@Controller
public class PageController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showpage(@PathVariable String page){
		return page;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}


}
