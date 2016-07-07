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
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.PageUtil;
import com.cesecsh.util.ResponseJsonUtil;


/**
 * 
 * 2016-06-25
 * @author Jesse 
 * 
 *
 */

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Resource(name="equipmentService")
	private EquipmentService service;
	
	@Resource(name="equipmentTypeService")
	private EquipmentTypeService service2;
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,Model model){
		String equipmentId = request.getParameter("equipmentId");
		Equipment equipment = this.service.select(equipmentId);
		model.addAttribute("equipment",equipment);
		return "equipment/showEquipment";
	}
	
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		
		String equipmentId = String.valueOf(System.currentTimeMillis());
		String equipmentName = request.getParameter("equipmentName");
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		String equipmentCode = request.getParameter("equipmentCode");
		String equipmentAddress = request.getParameter("equipmentAddress");
		String regionId = request.getParameter("regionId");
		
		Equipment equipment = new Equipment();
		EquipmentType equipmentType = new EquipmentType();
		equipmentType.setEquipmentTypeId(equipmentTypeId);
		equipment.setEquipmentType(equipmentType);
		equipment.setEquipmentAddress(equipmentAddress);
		equipment.setEquipmentCode(equipmentCode);
		equipment.setEquipmentId(equipmentId);
		equipment.setEquipmentName(equipmentName);
		
		equipment.setRegionId(regionId);
		
		
		String flag="false";
		try{
			this.service.insert(equipment);
			flag="success";
			
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String equipmentId = request.getParameter("equipmentId");
		String equipmentName = request.getParameter("equipmentName");
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		String equipmentCode = request.getParameter("equipmentCode");
		String equipmentAddress = request.getParameter("equipmentAddress");
		String regionId = request.getParameter("regionId");
		
		Equipment equipment = new Equipment();
		EquipmentType equipmentType = new EquipmentType();
		equipmentType.setEquipmentTypeId(equipmentTypeId);
		equipment.setEquipmentType(equipmentType);
		equipment.setEquipmentAddress(equipmentAddress);
		equipment.setEquipmentCode(equipmentCode);
		equipment.setEquipmentId(equipmentId);
		equipment.setEquipmentName(equipmentName);
		equipment.setRegionId(regionId);
		String flag="false";
		try{
			this.service.update(equipment);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/destroy")
	public void destroy(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String equipmentId = request.getParameter("equipmentId");
		Equipment equipment = new Equipment();
		equipment.setEquipmentId(equipmentId);
		String flag="false";
		try{
			this.service.delete(equipment);
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
	
		return "equipment/showEquipmentList";
	}
	
	
	
	@RequestMapping("/getListJson")
	public void getListJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String equipmentName = request.getParameter("equipmentName");
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		String equipmentCode = request.getParameter("equipmentCode");
		String equipmentAddress = request.getParameter("equipmentAddress");
		
		Equipment equipment = new Equipment();
		equipment.setEquipmentAddress(equipmentAddress);
		equipment.setEquipmentCode(equipmentCode);
		equipment.setEquipmentName(equipmentName);
		EquipmentType equipmentType = new EquipmentType();
		equipmentType.setEquipmentTypeId(equipmentTypeId);
		equipment.setEquipmentType(equipmentType);
    	List<String> sortColumns = new ArrayList<String>();
    	
    	sortColumns.add("T1.equipment_id");
    	sortColumns.add("T1.equipment_name");
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
        List<Equipment> listTotal = this.service.selectByConditions(pu.getMap()); 
        List<Equipment> allTotal = this.service.selects(); 
        
        Map map = new HashMap();
        map.put("total",allTotal.size());
        map.put("rows", listTotal);
        ResponseJsonUtil.responseToJson(response,map);

	}
	
	
	@RequestMapping("/getEquipmentTypeJson")
	public void getEquipmentTypeJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
				EquipmentType equipmentType = new EquipmentType();
				List<EquipmentType> list =  service2.selects();
		    	
		        List arrayList = new ArrayList();
		        for(EquipmentType e:list){
		        	 Map map = new HashMap();
		        	 map.put("id",e.getEquipmentTypeId());
		             map.put("name", e.getEquipmentTypeName());
		             arrayList.add(map);
		        }
		        ResponseJsonUtil.responseToJson(response,arrayList);

	}
	
	
	

}
