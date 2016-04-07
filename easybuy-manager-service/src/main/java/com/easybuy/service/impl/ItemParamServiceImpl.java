package com.easybuy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.mapper.TbItemParamMapper;
import com.easybuy.pojo.EasyUIDataGridResult;
import com.easybuy.pojo.EasybuyResult;
import com.easybuy.pojo.TbItemParam;
import com.easybuy.pojo.TbItemParamExample;
import com.easybuy.pojo.TbItemParamExample.Criteria;
import com.easybuy.service.ItemParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ItemParamServiceImpl implements ItemParamService{
	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	/**
	 * 获取商品规格参数列表
	 * <p>
	 * Title: getItemParamList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * @see com.easybuy.service.ItemService#getItemParamList(int, int)
	 */
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		// 执行分页
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemParamExample example = new TbItemParamExample();
		//包含大文本数据，需要用selectByExampleWithBLOBs
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);

		// 取分页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		// 返回出来结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}
	@Override
	public EasybuyResult getItemParamByCid(long cid) {
		//根据cid去查询规格参数是否存在
		TbItemParamExample example = new TbItemParamExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否有查询结果
		if(list!=null&&list.size()>0){
			return  EasybuyResult.ok(list.get(0));
		}
		return  EasybuyResult.ok();
	}
	
	
	

}
