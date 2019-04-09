package com.java.dao;


import java.util.List;
import java.util.Map;


import com.java.pojo.Tree;

 


public interface TreeDao {
	
	
	/**
	 * ���ݸ��ڵ�  ������  �������������ids֮��
	 * map �� father��-1�� ��   ids(1.2.5.6.9.4.4.)
	 * 
	 * mapֻ���ids���������� ids(1.2.5.6.9.4.4.)    �������Ӧ�þ�������realm ���� shire ��Ȩ��� ����ids��tree
	 * 
	 * ���ݸ��ڵ� �ò˵�  û�й�������  ��Ȩ���ڵ�ʱ��Ҫ��
	 */
	public List<Tree> getTreesByFatherOrIds(Map<String,Object> map);
	
	public Integer add(Tree tree);
	public Tree findById(Integer id);
	public List<Tree> findByRoleId(Integer id);
	public Integer delete(Integer id);
	public Integer findTotal(Map<String ,Object> map);
	public Integer update(Tree tree);
	public List<Tree> list(Map<String ,Object> map);
	
	
}