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
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentTypeAttrService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //ï¿½ï¿½Ê¾ï¿½Ì³ï¿½ï¿½ï¿½SpringJUnit4ClassRunnerï¿½ï¿½  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class EquipmentTypeAttrServiceTest {  
     private static Logger logger = Logger.getLogger(EquipmentTypeAttrServiceTest.class);  
     @Resource(name="equipmentTypeAttrService")
     private EquipmentTypeAttrService service; 
   
    @Test  
     public void testSelect() {  
    	EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
    	equipmentTypeAttr.setEquipmentTypeId("1456812490714");
 //   	equipmentTypeAttr.setPortId("1461131230158");
    	
    	EquipmentTypeAttr equipmentTypeAttrL = service.select(equipmentTypeAttr);  
    
        logger.info(JSON.toJSONString(equipmentTypeAttrL));  
     }  
    
    @Test
    public void testSelects() {  
       String equipmentTypeId = "1234567";
       List<EquipmentTypeAttr> list = service.selects("equipmentTypeId");  
     
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testSelectByConditions() { 
    	
    	EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
    	equipmentTypeAttr.setEquipmentTypeId("1456812490714");
//		equipmentTypeAttr.setPortName("¿ª");
		
    	Integer page=1;
    	Integer rows = 10;
    	String sortFlag = "desc";
 
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("port_id");
    	sortColumns.add("port_name");
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(equipmentTypeAttr);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<EquipmentTypeAttr> list = service.selectByConditions(pu.getMap());  
       logger.info(JSON.toJSONString(list));  
    }  
 }  
