package com.cesecsh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


/**
 * 
 * 2016-06-29
 * @author Jesse 
 * 
 *
 */

@Controller
@RequestMapping("/concentrator")
public class ConcentratorController {
	
	@Resource(name="concentratorService")
	private ConcentratorService service;
	
	@Resource(name="concentratorTypeService")
	private ConcentratorTypeService service2;
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,Model model){
		String conId = request.getParameter("conId");
		Concentrator concentrator = this.service.select(conId);
		model.addAttribute("concentrator",concentrator);
		return "concentrator/showConcentrator";
	}
	
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		
		
		
		
		String conId = String.valueOf(System.currentTimeMillis());
		String conName = request.getParameter("conName");
		String conTypeId = request.getParameter("conTypeId");
		String conCode = request.getParameter("conCode");
		String conIp = request.getParameter("conIp");
		String conPath = request.getParameter("conPath");
		
		Concentrator concentrator = new Concentrator();
		ConcentratorType concentratorType = new ConcentratorType();
		concentratorType.setConTypeId(conTypeId);
		concentrator.setConcentratorType(concentratorType);
		concentrator.setConCode(conCode);
		concentrator.setConId(conId);
		concentrator.setConIp(conIp);
		concentrator.setConName(conName);
		concentrator.setConPath(conPath);

		
		
		String flag="false";
		try{
			this.service.insert(concentrator);
			flag="success";
			
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String conId = request.getParameter("conId");
		String conName = request.getParameter("conName");
		String conTypeId = request.getParameter("conTypeId");
		String conCode = request.getParameter("conCode");
		String conIp = request.getParameter("conIp");
		String conPath = request.getParameter("conPath");
		
		Concentrator concentrator = new Concentrator();
		ConcentratorType concentratorType = new ConcentratorType();
		concentratorType.setConTypeId(conTypeId);
		concentrator.setConcentratorType(concentratorType);
		concentrator.setConCode(conCode);
		concentrator.setConId(conId);
		concentrator.setConIp(conIp);
		concentrator.setConName(conName);
		concentrator.setConPath(conPath);

		String flag="false";
		try{
			this.service.update(concentrator);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/destroy")
	public void destroy(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String conId = request.getParameter("conId");
		Concentrator concentrator = new Concentrator();
		concentrator.setConId(conId);
		
		String flag="false";
		try{
			this.service.delete(concentrator);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
		
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping("/destroys")
	public void destoroys(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String selectedIds = request.getParameter("selectedIds");
		System.out.println("selectedIds:"+selectedIds);
		String[] ids = selectedIds.split(":");
		String flag="false";
		try{
			this.service.deletes(ids);
			flag="success";
			 ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request,Model model){
	
		return "concentrator/showConcentratorList";
	}
	
	
	
	@RequestMapping("/getListJson")
	public void getListJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		
		

		String conName = request.getParameter("conName");
		String conTypeId = request.getParameter("conTypeId");
		String conCode = request.getParameter("conCode");
		Concentrator concentrator = new Concentrator();
		ConcentratorType concentratorType = new ConcentratorType();
		concentratorType.setConTypeId(conTypeId);
		
		concentrator.setConName(conName);
		concentrator.setConCode(conCode);
		concentrator.setConcentratorType(concentratorType);
		
    	List<String> sortColumns = new ArrayList<String>();
       	sortColumns.add("T1.con_name");
    	sortColumns.add("T1.con_id");
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
        List<Concentrator> allTotal = this.service.selects(); 
        
        Map map = new HashMap();
        map.put("total",allTotal.size());
        map.put("rows", listTotal);
        ResponseJsonUtil.responseToJson(response,map);

	}
	
	
	@RequestMapping("/getConcentratorTypeJson")
	public void getConcentratorTypeJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
				ConcentratorType concentratorType = new ConcentratorType();
				List<ConcentratorType> list =  service2.selects();
		    	
		        List arrayList = new ArrayList();
		        for(ConcentratorType e:list){
		        	 Map map = new HashMap();
		        	 map.put("id",e.getConTypeId());
		             map.put("name", e.getConTypeName());
		             arrayList.add(map);
		        }
		        ResponseJsonUtil.responseToJson(response,arrayList);

	}
	
	
	

}
