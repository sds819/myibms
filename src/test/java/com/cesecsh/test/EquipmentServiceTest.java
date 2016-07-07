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
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;
import com.cesecsh.util.ResponseJsonUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //ï¿½ï¿½Ê¾ï¿½Ì³ï¿½ï¿½ï¿½SpringJUnit4ClassRunnerï¿½ï¿½  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class EquipmentServiceTest {  
     private static Logger logger = Logger.getLogger(EquipmentServiceTest.class);  
     @Resource(name="equipmentService")
     private EquipmentService service; 
     @Resource(name="equipmentTypeService")
 	 private EquipmentTypeService service2;
   
    @Test  
     public void testSelect() {  
    	Equipment equipment = service.select("1432739037231");  
    
        logger.info(JSON.toJSONString(equipment)); 
        logger.info(JSON.toJSONString(equipment.getEquipmentType()));  
     }  
    
    @Test
    public void testSelects() {  
       List<Equipment> list = service.selects();  
     
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testGetListJson() { 
    	Equipment equipment = new Equipment();
    	equipment.setEquipmentName("ÑÌ¸Ð");
    	equipment.setEquipmentAddress("N006L02D063");
    	equipment.setEquipmentCode("N006L02D063");
    	EquipmentType equipmentType = new EquipmentType();
		equipmentType.setEquipmentTypeId("1421400687365");
		equipment.setEquipmentType(equipmentType);
    	Integer page=1;
    	Integer rows = 10;
    	
    	
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("T1.equipment_id");
    	sortColumns.add("T1.equipment_type_id");
    	sortColumns.add("T1.equipment_code");
    	sortColumns.add("T1.equipment_address");
    	String sortFlag = "desc";
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(equipment);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<Equipment> list = this.service.selectByConditions(pu.getMap()); 
      
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testGetEquipmentTypeJson() { 
    	EquipmentType equipmentType = new EquipmentType();

        List<EquipmentType> list = service2.selects(); 
        
        List arrayList = new ArrayList();
        for(EquipmentType e:list){
        	 Map map = new HashMap();
        	 map.put("id",e.getEquipmentTypeId());
             map.put("name", e.getEquipmentTypeName());
             arrayList.add(map);
        }
        
       
       
      
       logger.info(JSON.toJSONString(arrayList));  
    }  
 }  
