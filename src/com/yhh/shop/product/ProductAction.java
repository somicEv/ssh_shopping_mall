package com.yhh.shop.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yhh.shop.category.CateGory;
import com.yhh.shop.category.CateGoryService;
import com.yhh.shop.secondCateGory.SecondCateGory;
import com.yhh.shop.secondCateGory.SecondCateGoryService;
import com.yhh.shop.utils.PageUtils;

/**
 * 商品访问的Action类
 * @author 浩瀚
 *
 */
@SuppressWarnings("serial")
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	
	//Product实体对象
	private Product product = new Product();
	
	//接收Cid
	private Integer cid;
	
	//接收Page
	private Integer page;
	
	//接收CSid
	private Integer csid;
	
	//注入一级分类的Service
	@Resource(name="cateGoryService")
	private CateGoryService cateGoryService;
	
	//注入二级分类Service
	@Resource(name="secondCateGoryService")
	private SecondCateGoryService secondCateGoryService;
	
	//注入商品类的Service
	@Resource(name="productService")
	private ProductService productService;
	
	//用于分页显示显示商品的Bean
	private PageUtils<Product> pageBean;
	
	// 将要上传的文件
	private File upload;
	
	// 上传文件的类型
	private String uploadContentType;
	
	// 上传文件的名称
	private String uploadFileName;
	
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCsid() {
		return csid;
	}
	
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	
	public Integer getCid() {
		return cid;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public PageUtils<Product> getPageBean() {
		return pageBean;
	}
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	//查询商品的方法
	public String findByCid(){
 		//查询分类：
		//查询所有一级分类：
		List<CateGory> cateList = cateGoryService.findAll(); 
		//获得ValueStack对象,压入值栈
		ActionContext.getContext().getValueStack().set("cateList", cateList);
		
		//查询商品
		pageBean = productService.findByPage(cid,page);
		
		return "findByCidSuccess";
	}

	/**
	 * 查询商品详情的方法
	 */
	public String findByPid(){
		//查询分类：
		//查询所有一级分类：
		List<CateGory> cateList = cateGoryService.findAll(); 
		//获得ValueStack对象,压入值栈
		ActionContext.getContext().getValueStack().set("cateList", cateList);
		//查询到的商品详情
		product = productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}
	
	/**
	 * 查询二级分类商品详情的方法
	 * 
	 */
	public String findByCsid(){
		
		List<CateGory> categoryList = cateGoryService.findAll();
		// 获得值栈:
		ActionContext.getContext().getValueStack()
				.set("cateList", categoryList);
		
		pageBean = productService.findByCsid(csid,page);
		return "findByCsidSuccess";
	}

	/**
	 *  后台查询所有商品的方法
	 */
	public String adminFindAll(){
		pageBean = productService.findByPage(page);
		return "adminFindAllSuccess";
	}
	
	/**
	 *  后台：跳转添加商品页面
	 */
	public String addPage(){
		// 查询所有二级分类
		List<SecondCateGory> cslist = secondCateGoryService.findAll();
		// 将数据压栈
		ActionContext.getContext().getValueStack().set("cslist", cslist);
		
		return "addPageSuccess";
	}
	
	/**
	 *  后台：添加商品 上传商品图片
	 * @throws IOException 
	 */
	public String save() throws IOException{
		// 文件上传的操作:
		// 获得上传的路径:
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String realPath = path+"/"+csid+"/"+uploadFileName;
		File diskFile = new File(realPath);
		// 文件上传:
		FileUtils.copyFile(upload, diskFile);
		// 保存到数据库:
		// 设置二级分类
		SecondCateGory categorySecond = new SecondCateGory();
		categorySecond.setCsid(csid);
		product.setSecondCateGory(categorySecond);
		// 设置时间:
		product.setPdate(new Date());
		// 设置图片上传路径:
		product.setImage("products/"+csid+"/"+uploadFileName);
		
		// 调用Serviec保存商品:
		productService.save(product);
		
		return "saveSuccess";
	}

	
}
