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
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorTypeAttrService;
import com.cesecsh.service.ConcentratorTypeService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class ConcentratorTypeAttrServiceTest {  
     private static Logger logger = Logger.getLogger(ConcentratorTypeAttrServiceTest.class);  
     @Resource(name="concentratorTypeAttrService")
     private ConcentratorTypeAttrService service; 
   
    @Test  
     public void testSelect() {  
    	ConcentratorTypeAttr concentratorTypeAttr = new ConcentratorTypeAttr();  
    	concentratorTypeAttr.setConTypeId("1447663093899");
    	concentratorTypeAttr = this.service.select(concentratorTypeAttr);
        logger.info(JSON.toJSONString(concentratorTypeAttr));  
     }  
    
    @Test
    public void testSelects() {  
    	String conTypeId="1447663093899";
       List<ConcentratorTypeAttr> list = service.selects(conTypeId);  
     
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testSelectByConditions() { 
    	ConcentratorTypeAttr  concentratorTypeAttr = new ConcentratorTypeAttr();
    	concentratorTypeAttr.setPortName("״̬");;
    	Integer page=1;
    	Integer rows = 10;
    	String sortFlag = "desc";
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("port_id");
    	sortColumns.add("port_name");
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(concentratorTypeAttr);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<ConcentratorTypeAttr> list = service.selectByConditions(pu.getMap());  
       logger.info(JSON.toJSONString(list));  
    }  
 }  
