package com.easybuy.service;

import com.easybuy.pojo.EasyUIDataGridResult;
import com.easybuy.pojo.TbItem;

public interface ItemService {

	public TbItem getItemById(Long itemId);
	public EasyUIDataGridResult getItemList(int page, int rows);
}
