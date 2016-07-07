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
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //ï¿½ï¿½Ê¾ï¿½Ì³ï¿½ï¿½ï¿½SpringJUnit4ClassRunnerï¿½ï¿½  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class EquipmentTypeServiceTest {  
     private static Logger logger = Logger.getLogger(EquipmentTypeServiceTest.class);  
     @Resource(name="equipmentTypeService")
     private EquipmentTypeService service; 
   
    @Test  
     public void testSelect() {  
    	EquipmentType equipmentType = service.select("1429077147201");  
    
        logger.info(JSON.toJSONString(equipmentType));  
     }  
    
    @Test
    public void testSelects() {  
       List<EquipmentType> list = service.selects();  
     
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testSelectByConditions() { 
    	EquipmentType equipmentType = new EquipmentType();
    	equipmentType.setEquipmentTypeName("µç");
    	Integer page=1;
    	Integer rows = 10;
    	String sortFlag = "desc";
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("equipment_type_id");
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(equipmentType);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<EquipmentType> list = service.selectByConditions(pu.getMap());  
       logger.info(JSON.toJSONString(list));  
    }  
 }  
