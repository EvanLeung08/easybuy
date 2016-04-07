package com.easybuy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.mapper.TbItemDescMapper;
import com.easybuy.mapper.TbItemMapper;
import com.easybuy.mapper.TbItemParamItemMapper;
import com.easybuy.mapper.TbItemParamMapper;
import com.easybuy.pojo.EasyUIDataGridResult;
import com.easybuy.pojo.EasybuyResult;
import com.easybuy.pojo.TbItem;
import com.easybuy.pojo.TbItemDesc;
import com.easybuy.pojo.TbItemExample;
import com.easybuy.pojo.TbItemParamExample;
import com.easybuy.pojo.TbItemExample.Criteria;
import com.easybuy.pojo.TbItemParam;
import com.easybuy.service.ItemService;
import com.easybuy.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamMapper tbItemParamMapper;

	@Override
	public TbItem getItemById(Long itemId) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> tbItemList = tbItemMapper.selectByExample(example);
		if (tbItemList != null && tbItemList.size() > 0) {
			TbItem tbItem = tbItemList.get(0);
			return tbItem;
		}
		return null;
	}

	/**
	 * 获取商品列表
	 * <p>
	 * Title: getItemList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param page
	 * @param rows
	 * @return
	 * @see com.easybuy.service.ItemService#getItemList(int, int)
	 */
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 分页处理
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(pageInfo.getList());

		return result;
	}

	

	/**
	 * 创建商品
	 * <p>
	 * Title: createItem
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param item
	 * @param desc
	 * @return
	 * @see com.easybuy.service.ItemService#createItem(com.easybuy.pojo.TbItem,
	 *      java.lang.String)
	 */
	@Override
	public EasybuyResult createItem(TbItem item, String desc) {
		// 生成商品ID
		long itemId = IDUtils.genItemId();
		// 填充商品属性
		item.setId(itemId);
		// 商品状态 '商品状态，1-正常，2-下架，3-删除'
		item.setStatus((byte) 1);
		// 创建时间和更新时间
		Date curDate = new Date();
		item.setCreated(curDate);
		item.setUpdated(curDate);
		// 插入商品信息
		tbItemMapper.insert(item);
		// 维护商品描述属性
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(curDate);
		itemDesc.setUpdated(curDate);
		// 插入数据到商品描述表中
		tbItemDescMapper.insert(itemDesc);

		return EasybuyResult.ok();
	}

}
