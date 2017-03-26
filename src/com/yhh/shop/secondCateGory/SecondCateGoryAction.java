package com.yhh.shop.secondCateGory;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yhh.shop.category.CateGory;
import com.yhh.shop.category.CateGoryService;
import com.yhh.shop.utils.PageUtils;

public class SecondCateGoryAction extends ActionSupport implements ModelDriven<SecondCateGory> {
	// 接收参数：页数 
	private Integer page;
	
	// 接收参数：一级分类ID
	private Integer cid;
	
	// 模型驱动
	private SecondCateGory secondCateGory = new SecondCateGory();
	
	//注入二级分类Service
	@Resource(name="secondCateGoryService")
	private SecondCateGoryService secondCateGoryService;
	
	//注入一级分类的Service
	@Resource(name="cateGoryService")
	private CateGoryService cateGoryService;
	
	@Override
	public SecondCateGory getModel() {
		return secondCateGory;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	/**
	 *  二级分类管理：查询所有二级分类（带有分页）
	 */
	public String adminFindAll(){
		// 调用Service完成分页
		PageUtils<SecondCateGory> pageBean = secondCateGoryService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	
	/**
	 * 二级分类管理：跳转到添加页面的方法
	 * @return
	 */
	public String addPage(){
		// 查询一级分类列表
		List<CateGory> list = cateGoryService.findAll();
		ActionContext.getContext().getValueStack().set("cateGoryList", list);
		return "addPageSuccess";
	}

	/**
	 * 二级分类管理：添加二级分类
	 */
	public String save(){
		// 添加相应的一级分类
		CateGory cateGory = new CateGory();
		cateGory.setCid(cid);
		secondCateGory.setCateGory(cateGory);
		// 调用Service
		secondCateGoryService.save(secondCateGory);
		
		return "saveSuccess";
	}
	
	/**
	 * 二级分类管理：跳转到修改页面
	 */
	public String edit(){
		secondCateGory = secondCateGoryService.edit(secondCateGory);
		addPage();
		return "editSuccess";
	}

	/**
	 * 二级分类管理：修改二级分类
	 */
	public String update(){
		CateGory cateGory = new CateGory();
		cateGory.setCid(cid);
		secondCateGory.setCateGory(cateGory);
		secondCateGoryService.update(secondCateGory);
		
		return "updateSuccess";
	}
	
	
	
}
