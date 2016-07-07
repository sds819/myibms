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
import com.cesecsh.model.ConcentratorParam;
import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorParamService;
import com.cesecsh.service.ConcentratorTypeAttrService;
import com.cesecsh.service.ConcentratorTypeService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //��ʾ�̳���SpringJUnit4ClassRunner��  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class ConcentratorParamServiceTest {  
     private static Logger logger = Logger.getLogger(ConcentratorParamServiceTest.class);  
     @Resource(name="concentratorParamService")
     private ConcentratorParamService service; 
   
    @Test  
     public void testSelect() {  
    	ConcentratorParam concentratorParam = new ConcentratorParam();  
    	concentratorParam.setConId("1444615115140");
    	concentratorParam = this.service.select(concentratorParam);

        logger.info(JSON.toJSONString(concentratorParam));  
     }  
    
    @Test
    public void testSelects() {  
    	String conId="1444615115140";
       List<ConcentratorParam> list = service.selects(conId);  
     
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testSelectByConditions() { 
    	
    	
		String conId = "1444615115140";

		ConcentratorParam concentratorParam = new ConcentratorParam();
		concentratorParam.setConId(conId);

		Integer page = 1;
		Integer rows = 10;
		String sortFlag = "desc";
		
    	
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("con_param_id");
    	sortColumns.add("con_param");
    	
    	
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(concentratorParam);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<ConcentratorParam> listTotal = this.service.selectByConditions(pu.getMap()); 
       logger.info(JSON.toJSONString(listTotal));  
    }  
 }  
