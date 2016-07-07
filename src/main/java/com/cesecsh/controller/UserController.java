package com.cesecsh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cesecsh.model.User;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;


/**
 * 
 * 2016-06-21
 * @author Jesse 
 * 
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/getUser")
	public String getUser(HttpServletRequest request,Model model){
		Long id = Long.parseLong(request.getParameter("id"));
		User user = this.userService.selectUser(id);
		model.addAttribute("user",user);
		return "user/showUser";
	}
	
	
	
	@RequestMapping("/saveUser")
	public void saveUser(HttpServletRequest request,HttpServletResponse response){
		
		Long id = System.currentTimeMillis();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setUsername(username);
		String flag="false";
		try{
			this.userService.insertUser(user);
			flag="success";
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(flag).toString());
			response.getWriter().flush();
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@RequestMapping("/updateUser")
	public void updateUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Long id = Long.parseLong(request.getParameter("id"));
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setUsername(username);
		
		String flag="false";
		try{
			this.userService.updateUser(user);
			flag="success";
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(flag).toString());
			response.getWriter().flush();
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/destroyUser")
	public void destoryUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Long id = Long.parseLong(request.getParameter("id"));
		User user = new User();
		user.setId(id);
		String flag="false";
		try{
			this.userService.deleteUser(user);
			flag="success";
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(flag).toString());
			response.getWriter().flush();
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping("/destroyUsers")
	public void destoryUsers(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String selectedIds = request.getParameter("selectedIds");
		System.out.println("selectedIds:"+selectedIds);
		String[] ids = selectedIds.split(":");
		String flag="false";
		try{
			this.userService.deleteUsers(ids);
			flag="success";
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(flag).toString());
			response.getWriter().flush();
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/getUsers")
	public String getUsers(HttpServletRequest request,Model model){
	
		return "user/showUserList";
	}
	
	
	
	@RequestMapping("/getUsersJson")
	public void getUsersJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		
		String username = request.getParameter("uasername");
		
		User user = new User();
		user.setUsername(username);
		
    	
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("id");
    	sortColumns.add("user_name");
    	String sortFlag = "asc";
    	/**
    	 * 
    	 */
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(user);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<User> userList = userService.selectUserByConditions(pu.getMap()); 
        List<User> userTotal = userService.selectUsers(); 
        
        Map map = new HashMap();
        map.put("total",userTotal.size());
        map.put("rows", userList);
		
       
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(map).toString());
		response.getWriter().flush();
		
		
		
	}
	
	
	

}
