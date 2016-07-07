package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.User;


public interface UserService {
	
	
	public int insertUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(User user);
	
	public int deleteUsers(String[] ids);
	
	public User selectUser(Long id); 
	
	public List<User> selectUsers();
	
	public List<User> selectUserByConditions(Map map);
	
	/*
	  public int deleteByPrimaryKey(Integer id);

	  public int insert(User record);

	  public int insertSelective(User record);

	  public  User selectByPrimaryKey(Integer id);
	    
	  public  List<User> selectByPrimarySelective();

	  public  int updateByPrimaryKeySelective(User record);

	  public  int updateByPrimaryKey(User record);
	 
	 */
	    
	
	





}
