package com.yhh.shop.category;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=false)
public class CateGoryService {
	//注解方式，注入Dao
	@Resource(name="cateGoryDao")
	private CateGoryDao cateGoryDao;
	
	//查询所有一级分类
	public List<CateGory> findAll() {
		return cateGoryDao.findAll();
	}

	public void save(CateGory cateGory) {
		// TODO Auto-generated method stub
		cateGoryDao.save(cateGory);
	}

	public void delete(CateGory cateGory) {
		// TODO Auto-generated method stub
		cateGoryDao.delete(cateGory);
	}

	public CateGory findByCid(Integer cid) {
		// TODO Auto-generated method stub
		
		return cateGoryDao.findByCid(cid);
	}

	public void update(CateGory cateGory) {
		// TODO Auto-generated method stub
		cateGoryDao.update(cateGory);
	}
}
