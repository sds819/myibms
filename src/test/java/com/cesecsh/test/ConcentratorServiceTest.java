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
import com.cesecsh.model.Concentrator;
import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorService;
import com.cesecsh.service.ConcentratorTypeService;
import com.cesecsh.service.EquipmentService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;
import com.cesecsh.util.ResponseJsonUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class ConcentratorServiceTest {  
     private static Logger logger = Logger.getLogger(ConcentratorServiceTest.class);  
     @Resource(name="concentratorService")
     private ConcentratorService service; 
     @Resource(name="concentratorTypeService")
 	 private ConcentratorTypeService service2;
   
    @Test  
     public void testSelect() {  
    	Concentrator concentrator = service.select("1417336123985");  
    
        logger.info(JSON.toJSONString(concentrator)); 
        logger.info(JSON.toJSONString(concentrator.getConcentratorType()));  
     }  
    
    @Test
    public void testSelects() {  
       List<Concentrator> list = service.selects();  
     
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testGetListJson() { 
    	Integer page = 1;
		Integer rows = 10;
		
		

		String conName = "¥";
		String conTypeId = null;
		String conCode = null;
		Concentrator concentrator = new Concentrator();
		ConcentratorType concentratorType = new ConcentratorType();
		concentratorType.setConTypeId(conTypeId);
		
		concentrator.setConName(conName);
		concentrator.setConCode(conCode);
		concentrator.setConcentratorType(concentratorType);
		
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("T1.con_id");
    	sortColumns.add("T1.con_name");
    	sortColumns.add("T1.con_type_id");
    	sortColumns.add("T1.con_code");
    
    	String sortFlag = "desc";
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(concentrator);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<Concentrator> listTotal = this.service.selectByConditions(pu.getMap());
      
       logger.info(JSON.toJSONString(listTotal));  
    }  
    
    @Test
    public void testGetEquipmentTypeJson() { 

		ConcentratorType concentratorType = new ConcentratorType();
		List<ConcentratorType> list =  service2.selects();
    	
        List arrayList = new ArrayList();
        for(ConcentratorType e:list){
        	 Map map = new HashMap();
        	 map.put("id",e.getConTypeId());
             map.put("name", e.getConTypeName());
             arrayList.add(map);
        }
       
       
      
       logger.info(JSON.toJSONString(arrayList));  
    }  
 }  
