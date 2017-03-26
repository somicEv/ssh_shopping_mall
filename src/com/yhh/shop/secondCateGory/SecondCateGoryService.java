package com.yhh.shop.secondCateGory;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.yhh.shop.utils.PageUtils;

@Transactional(readOnly=false)
public class SecondCateGoryService {
	
	// 注入Dao
	@Resource(name="secondCateGoryDao")
	private SecondCateGoryDao secondCateGoryDao;
	
	//带有分页查询二级分类
	public PageUtils<SecondCateGory> findByPage(Integer page) {
		
		PageUtils<SecondCateGory> pageBean = new PageUtils<SecondCateGory>();
		
		// 存储当前页数
		pageBean.setPage(page);
		
		// 设置每页最大数量
		Integer limit = 10;
		pageBean.setLimit(limit); 
		
		//查询总记录数
		Integer totalCount = secondCateGoryDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		//计算总页数
		Integer totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		// 查询每一页二级分类
		Integer begin = (page - 1)*limit;
		List<SecondCateGory> list = secondCateGoryDao.findAll(begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	public void save(SecondCateGory secondCateGory) {
		secondCateGoryDao.save(secondCateGory);
	}

	public SecondCateGory edit(SecondCateGory secondCateGory) {
		// TODO Auto-generated method stub
		return secondCateGoryDao.findByCsid(secondCateGory);
	}

	public void update(SecondCateGory secondCateGory) {
		// TODO Auto-generated method stub
		secondCateGoryDao.update(secondCateGory);
	}

	public List<SecondCateGory> findAll() {
		return secondCateGoryDao.findAll();
	}

	
}
