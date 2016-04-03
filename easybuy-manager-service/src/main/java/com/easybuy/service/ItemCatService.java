package com.easybuy.service;

import java.util.List;

import com.easybuy.pojo.EasyUITreeNode;
/**
 * 
 * <p>Title: ItemCatService</p>
 * <p>Description: </p>
 * <p>Company: www.evanshare.com</p> 
 * @author	Evan
 * @date	2016年3月2日上午12:54:23
 * @version 1.0
 */
public interface ItemCatService {

	List<EasyUITreeNode> getItemCatList(long parentId);

}
