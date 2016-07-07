package com.cesecsh.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.cesecsh.model.User;
import com.cesecsh.service.UserService;
import com.cesecsh.service.impl.UserServiceImpl;
import com.cesecsh.util.PageUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //ï¿½ï¿½Ê¾ï¿½Ì³ï¿½ï¿½ï¿½SpringJUnit4ClassRunnerï¿½ï¿½  
 @ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class UserServiceTest {  
     private static Logger logger = Logger.getLogger(UserServiceTest.class);  
 //  private ApplicationContext ac = null;  
     @Resource(type=UserServiceImpl.class)
     private UserService service = null;  
   
 //  @Before  
 //  public void before() {  
 //      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
 //      userService = (IUserService) ac.getBean("userService");  
 //  }  
   
    @Test  
     public void testSelectUser() {  
         User user = service.selectUser(1L);  
    
        logger.info(JSON.toJSONString(user));  
     }  
    
    @Test
    public void testSelectUsers() {  
       List<User> userList = service.selectUsers();  
     
       logger.info(JSON.toJSONString(userList));  
    }  
    
    @Test
    public void testSelectUserByConditions() { 
    	User user = new User();
    	user.setUsername("²âÊÔ");
    	Integer page=1;
    	Integer rows = 10;
    	String sortFlag = "asc";
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("id");
    	sortColumns.add("user_name");
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(user);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<User> userList = service.selectUserByConditions(pu.getMap());  
       logger.info(JSON.toJSONString(userList));  
    }  
 }  
