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
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;
import com.cesecsh.util.ResponseJsonUtil;


/**
 * 
 * 2016-06-22
 * @author Jesse 
 * 
 *
 */

@Controller
@RequestMapping("/equipmentType")
public class EquipmentTypeController {
	
	@Resource(name="equipmentTypeService")
	private EquipmentTypeService service;
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,Model model){
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		EquipmentType equipmentType = this.service.select(equipmentTypeId);
		model.addAttribute("equipmentType",equipmentType);
		return "equipmentType/showEquipmentType";
	}
	
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		
		String equipmentTypeId = String.valueOf(System.currentTimeMillis());
		String equipmentTypeName = request.getParameter("equipmentTypeName");
		String equipmentTypeCode = request.getParameter("equipmentTypeCode");
		
		EquipmentType equipmentType = new EquipmentType();
		equipmentType.setEquipmentTypeId(equipmentTypeId);
		equipmentType.setEquipmentTypeCode(equipmentTypeCode);
		equipmentType.setEquipmentTypeName(equipmentTypeName);
		
		String flag="false";
		try{
			this.service.insert(equipmentType);
			flag="success";
			
			ResponseJsonUtil.responseToJson(response,flag);
			/*
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(flag).toString());
			response.getWriter().flush();
			*/
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		String equipmentTypeName = request.getParameter("equipmentTypeName");
		String equipmentTypeCode = request.getParameter("equipmentTypeCode");
		
		EquipmentType equipmentType = new EquipmentType();
		equipmentType.setEquipmentTypeId(equipmentTypeId);
		equipmentType.setEquipmentTypeCode(equipmentTypeCode);
		equipmentType.setEquipmentTypeName(equipmentTypeName);
		String flag="false";
		try{
			this.service.update(equipmentType);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			/*
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(flag).toString());
			response.getWriter().flush();
			*/
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/destroy")
	public void destroy(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		EquipmentType equipmentType = new EquipmentType();
		equipmentType.setEquipmentTypeId(equipmentTypeId);
		String flag="false";
		try{
			this.service.delete(equipmentType);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			/*
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(flag).toString());
			response.getWriter().flush();
			*/
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
			/*
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(flag).toString());
			response.getWriter().flush();
			*/
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request,Model model){
	
		return "equipmentType/showEquipmentTypeList";
	}
	
	
	
	@RequestMapping("/getListJson")
	public void getListJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		
		String equipmentTypeName = request.getParameter("equipmentTypeName");
		
		EquipmentType equipmentType = new EquipmentType();
		equipmentType.setEquipmentTypeName(equipmentTypeName);
		
		
    	
    	List<String> sortColumns = new ArrayList<String>();
    	sortColumns.add("equipment_type_id");
    	sortColumns.add("equipment_type_name");
    	String sortFlag = "desc";
    	/**
    	 * 
    	 */
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(equipmentType);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map:"+pu.getMap());
        List<EquipmentType> listTotal = this.service.selectByConditions(pu.getMap()); 
        List<EquipmentType> allTotal = this.service.selects(); 
        
        Map map = new HashMap();
        map.put("total",allTotal.size());
        map.put("rows", listTotal);
		
       
        ResponseJsonUtil.responseToJson(response,map);
        /*
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(map).toString());
		response.getWriter().flush();
		*/
		
		
		
	}
	
	
	

}
