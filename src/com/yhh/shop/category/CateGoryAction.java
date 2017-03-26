package com.yhh.shop.category;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class CateGoryAction extends ActionSupport implements ModelDriven<CateGory>{
	
	private CateGory cateGory = new CateGory();
	
	@Resource(name="cateGoryService")
	private CateGoryService cateGoryService;
	
	@Override
	public CateGory getModel() {
		// TODO Auto-generated method stub
		return cateGory;
	}
	
	/**
	 * 后台：查询所有一级分类的方法
	 */
	public String adminFindAll(){
		//查询所有分类
		List<CateGory> categoryList = cateGoryService.findAll();
		//将数据压入值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "adminFindAllSuccess";
	}

	/**
	 * 后台：保存一级分类
	 */
	public String save(){
		cateGoryService.save(cateGory);
		return "adminSaveCateGorySuccess";
	}
	
	/**
	 * 后台：删除一级分类
	 */
	public String delete(){
		cateGoryService.delete(cateGory);
		return "adminDeleteSuccess";
	}
	
	/**
	 * 后台：编辑一级分类
	 */
	public String edit(){
		cateGory = cateGoryService.findByCid(cateGory.getCid());
		return "adminEditSuccess";
	}
	
	/**
	 * 后台：修改一级分类
	 * @return
	 */
	public String update(){
		cateGoryService.update(cateGory);
		return "adminUpdateSuccess";
		
	}
}
