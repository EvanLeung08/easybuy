package com.easybuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easybuy.pojo.EasyUIDataGridResult;
import com.easybuy.pojo.EasybuyResult;
import com.easybuy.service.ItemParamService;
import com.easybuy.service.ItemService;
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public EasybuyResult getItemParamByCid(@PathVariable long itemCatId){
		EasybuyResult result = 	itemParamService.getItemParamByCid(itemCatId);
		
		return result;
	}
}
