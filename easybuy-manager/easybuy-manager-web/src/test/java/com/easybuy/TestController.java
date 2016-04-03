package com.easybuy;

import java.awt.event.ItemListener;
import java.util.List;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easybuy.mapper.TbItemMapper;
import com.easybuy.pojo.TbItem;
import com.easybuy.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
public class TestController {
	
	
	@Test
	public void testPageHelper(){
		ApplicationContext ctx =  new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		
		TbItemMapper itemMapper = ctx.getBean(TbItemMapper.class);
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(1, 10);
		
		 List<TbItem> itemList = itemMapper.selectByExample(example);
		for( TbItem item :itemList){
			System.out.println(item.getTitle());
		}
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
		System.out.println(pageInfo.getTotal());
	}

}
