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
import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorTypeService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //ï¿½ï¿½Ê¾ï¿½Ì³ï¿½ï¿½ï¿½SpringJUnit4ClassRunnerï¿½ï¿½  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class ConcentratorTypeServiceTest {  
     private static Logger logger = Logger.getLogger(ConcentratorTypeServiceTest.class);  
     @Resource(name="concentratorTypeService")
     private ConcentratorTypeService service; 
   
    @Test  
     public void testSelect() {  
    	 ConcentratorType  concentratorType = service.select("1447663093899");  
        logger.info(JSON.toJSONString(concentratorType));  
     }  
    
    @Test
    public void testSelects() {  
       List<ConcentratorType> list = service.selects();  
     
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testSelectByConditions() { 
    	ConcentratorType  concentratorType = new ConcentratorType();
    	concentratorType.setConTypeName("µç");
    	Integer page=1;
    	Integer rows = 10;
    	String sortFlag = "desc";
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("con_type_id");
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(concentratorType);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<ConcentratorType> list = service.selectByConditions(pu.getMap());  
       logger.info(JSON.toJSONString(list));  
    }  
 }  
