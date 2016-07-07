package com.cesecsh.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentInf;
import com.cesecsh.model.EquipmentInfType;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentInfService;
import com.cesecsh.service.EquipmentService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;
import com.cesecsh.util.ResponseJsonUtil;



@RunWith(SpringJUnit4ClassRunner.class)     //ï¿½ï¿½Ê¾ï¿½Ì³ï¿½ï¿½ï¿½SpringJUnit4ClassRunnerï¿½ï¿½  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
   
 public class EquipmentInfServiceTest {  
     private static Logger logger = Logger.getLogger(EquipmentInfServiceTest.class);  
     @Resource(name="equipmentInfService")
     private EquipmentInfService service; 
     @Resource(name="dataSource")
     private DataSource dataSource;
    
   
    @Test  
     public void testSelect() {  
    	String equipmentInfId="1461135439875";
    	EquipmentInf e = new EquipmentInf();
    	e.setEquipmentInfId(equipmentInfId);
    	EquipmentInf equipment = service.select(e);  
    	 logger.info(JSON.toJSONString(equipment)); 
         logger.info(JSON.toJSONString(equipment.getConcentrator()));  
         logger.info(JSON.toJSONString(equipment.getConcentratorTypeAttr()));  
         logger.info(JSON.toJSONString(equipment.getEquipmentInfType())); 
         logger.info(JSON.toJSONString(equipment.getEquipmentTypeAttr()));  
         logger.info(JSON.toJSONString(equipment.getValueType()));  
    	
    	/*
    	EquipmentInf equipment = service.select("1432739037231");  
    
        logger.info(JSON.toJSONString(equipment)); 
        logger.info(JSON.toJSONString(equipment.getEquipmentType()));  
        */
     }  
    
    @Test
    public void testSelects() {  
       String equipmentId="1461135439872";
       List<EquipmentInf> list = service.selects(equipmentId);  
     
       logger.info(JSON.toJSONString(list));  
    }  
    
    @Test
    public void testGetListJson() { 
    	EquipmentInf equipmentInf = new EquipmentInf();
    	equipmentInf.setEquipmentInfName("");
    	EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
    	equipmentTypeAttr.setPortName("¿ª");
    	equipmentInf.setEquipmentTypeAttr(equipmentTypeAttr);
    	Integer page=1;
    	Integer rows = 10;
    	
    	
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("equipment_inf_id");
    	sortColumns.add("equipment_inf_name");
    	
    	String sortFlag = "desc";
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(equipmentInf);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<EquipmentInf> list = this.service.selectByConditions(pu.getMap()); 
      
       logger.info(JSON.toJSONString(list));  
    }  
    
    
    @Test
    public void testEquipmentInfTypeJson(){
    	String sql=" select equipment_inf_type_id,code,state,type_value from cec_equipment_inf_type ";
    	JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
    	DataSource ds = jdbcTemplate.getDataSource();
    	Connection conn = null;
    	Statement st = null;
    	ResultSet rs = null;
    	List<EquipmentInfType> lists = new ArrayList();
		
    	try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				while(rs.next()){
					EquipmentInfType eif = new EquipmentInfType();
					eif.setEquipmentInfTypeId(rs.getString("equipment_inf_type_id"));
					eif.setCode(rs.getString("code"));
					eif.setState(rs.getString("state"));
					eif.setTypeValue(rs.getString("type_value"));
					lists.add(eif);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
				try {
					if(rs!=null){
						rs.close();
					}
					if(st!=null){
						st.close();
					}
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}

    	for(EquipmentInfType eti:lists){
    		System.out.println(eti);
    	}
  //  	System.out.println(lists);
  //  	System.out.println(lists);
    	
    	
    }
    /*
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
      */  
 }  
