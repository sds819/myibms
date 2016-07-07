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
import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorTypeService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;
import com.cesecsh.util.ResponseJsonUtil;


/**
 * 
 * 2016-06-28
 * @author Jesse 
 * 
 *
 */

@Controller
@RequestMapping("/concentratorType")
public class ConcentratorTypeController {
	
	@Resource(name="concentratorTypeService")
	private ConcentratorTypeService service;
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,Model model){
		String conTypeId = request.getParameter("conTypeId");
		ConcentratorType concentratorType = this.service.select(conTypeId);
		model.addAttribute("concentratorType",concentratorType);
		return "concentratorType/showConcentratorType";
	}
	
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		
		String conTypeId = String.valueOf(System.currentTimeMillis());
		String conTypeName = request.getParameter("conTypeName");
		String conParam = request.getParameter("conParam");
		
		
		ConcentratorType concentratorType = new ConcentratorType();
		concentratorType.setConTypeId(conTypeId);
		concentratorType.setConTypeName(conTypeName);
		concentratorType.setConParam(conParam);
		
		
		String flag="false";
		try{
			this.service.insert(concentratorType);
			flag="success";
			
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String conTypeId = request.getParameter("conTypeId");
		String conTypeName = request.getParameter("conTypeName");
		String conParam = request.getParameter("conParam");
		
		
		ConcentratorType concentratorType = new ConcentratorType();
		concentratorType.setConTypeId(conTypeId);
		concentratorType.setConTypeName(conTypeName);
		concentratorType.setConParam(conParam);
		String flag="false";
		try{
			this.service.update(concentratorType);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/destroy")
	public void destroy(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String conTypeId = request.getParameter("conTypeId");
		ConcentratorType concentratorType = new ConcentratorType();
		concentratorType.setConTypeId(conTypeId);
	
		String flag="false";
		try{
			this.service.delete(concentratorType);
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
	
		return "concentratorType/showConcentratorTypeList";
	}
	
	
	
	@RequestMapping("/getListJson")
	public void getListJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		
		String conTypeName = request.getParameter("conTypeName");
		
		ConcentratorType concentratorType = new ConcentratorType();
		concentratorType.setConTypeName(conTypeName);
		
		
		
    	
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("con_type_id");
    	sortColumns.add("con_type_name");
    	String sortFlag = "desc";
    	/**
    	 * 
    	 */
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(concentratorType);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<ConcentratorType> listTotal = this.service.selectByConditions(pu.getMap()); 
        List<ConcentratorType> allTotal = this.service.selects(); 
        
        Map map = new HashMap();
        map.put("total",allTotal.size());
        map.put("rows", listTotal);
		
       
        ResponseJsonUtil.responseToJson(response,map);
     
		
	}
	
	
	

}
