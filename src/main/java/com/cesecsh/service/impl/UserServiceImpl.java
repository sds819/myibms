package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.User;
import com.cesecsh.service.UserService;

@Service("/userService")
public class UserServiceImpl implements UserService {

	@Resource UserService  service;
	
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return this.service.insertUser(user);
	}

	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return this.service.updateUser(user);
	}

	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return this.service.deleteUser(user);
	}

	

	public List<User> selectUsers() {
		// TODO Auto-generated method stub
		return this.service.selectUsers();
	}

	public User selectUser(Long id) {
		// TODO Auto-generated method stub
		return this.service.selectUser(id);
	}

	public List<User> selectUserByConditions(Map map) {
		// TODO Auto-generated method stub
		return this.service.selectUserByConditions(map);
	}

	public int deleteUsers(String[] ids) {
		// TODO Auto-generated method stub
		return this.service.deleteUsers(ids);
	}

}
