package com.easybuy.service;

import com.easybuy.pojo.EasyUIDataGridResult;
import com.easybuy.pojo.EasybuyResult;

public interface ItemParamService {
	public EasyUIDataGridResult getItemParamList(int page, int rows);
	public EasybuyResult getItemParamByCid(long cid);
}
