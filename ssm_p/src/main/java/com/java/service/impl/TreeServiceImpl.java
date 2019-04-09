package com.java.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.java.dao.TreeDao;
import com.java.pojo.Tree;
import com.java.service.TreeService;

import javax.annotation.Resource;



@Service("treeService")
public class TreeServiceImpl implements TreeService {

	@Resource
	private TreeDao  treeDao;



	public Tree findById(Integer id) {
		return treeDao.findById(id);
	}
	
	

	public List<Tree> getTreesByFatherOrIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return treeDao.getTreesByFatherOrIds(map);
	}



	public Integer add(Tree tree) {
		// TODO Auto-generated method stub
		return treeDao.add(tree);
	}



	public List<Tree> findByRoleId(Integer id) {
		// TODO Auto-generated method stub
		return treeDao.findByRoleId(id);
	}



	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return treeDao.delete(id);
	}


	public Integer update(Tree tree) {
		// TODO Auto-generated method stub
		return treeDao.update(tree);
	}



	public List<Tree> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return treeDao.list(map);
	}



	public Integer findTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return treeDao.findTotal(map);
	}
 
	


	 
}
