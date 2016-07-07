package com.cesecsh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.EquipmentTypeAttrService;
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
@RequestMapping("/equipmentTypeAttr")
public class EquipmentTypeAttrController {
	
	@Resource(name="equipmentTypeAttrService")
	private EquipmentTypeAttrService service;
	
	
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,Model model){
		EquipmentTypeAttr eta=new  EquipmentTypeAttr();
		String portId = request.getParameter("portId");
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		eta.setEquipmentTypeId(equipmentTypeId);
		eta.setPortId(portId);
		EquipmentTypeAttr equipmentTypeAttr = this.service.select(eta);
		model.addAttribute("equipmentTypeAttr",equipmentTypeAttr);
		return "equipmentType/showEquipmentTypeAttr";
	}
	
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		
		String portId = String.valueOf(System.currentTimeMillis());
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		String portName = request.getParameter("portName");
		String portCode = request.getParameter("portCode");
		String focus1 = request.getParameter("focus1");
		String focus2 = request.getParameter("focus2");
		String focus3 = request.getParameter("focus3");
		
	
		
		EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
		equipmentTypeAttr.setEquipmentTypeId(equipmentTypeId);
		equipmentTypeAttr.setFocus1(focus1);
		equipmentTypeAttr.setFocus2(focus2);
		equipmentTypeAttr.setFocus3(focus3);
		equipmentTypeAttr.setPortCode(portCode);
		equipmentTypeAttr.setPortId(portId);
		equipmentTypeAttr.setPortName(portName);
	
		
		String flag="false";
		try{
			this.service.insert(equipmentTypeAttr);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String portId = request.getParameter("portId");
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		String portName = request.getParameter("portName");
		String portCode = request.getParameter("portCode");
		String focus1 = request.getParameter("focus1");
		String focus2 = request.getParameter("focus2");
		String focus3 = request.getParameter("focus3");
		
	
		
		EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
		equipmentTypeAttr.setEquipmentTypeId(equipmentTypeId);
		equipmentTypeAttr.setFocus1(focus1);
		equipmentTypeAttr.setFocus2(focus2);
		equipmentTypeAttr.setFocus3(focus3);
		equipmentTypeAttr.setPortCode(portCode);
		equipmentTypeAttr.setPortId(portId);
		equipmentTypeAttr.setPortName(portName);
	
		String flag="false";
		try{
			this.service.update(equipmentTypeAttr);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/destroy")
	public void destroy(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String portId = request.getParameter("portId");
		EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
		equipmentTypeAttr.setPortId(portId);
		String flag="false";
		try{
			this.service.delete(equipmentTypeAttr);
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
	
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		model.addAttribute("equipmentTypeId",equipmentTypeId);
		return "equipmentType/showEquipmentTypeAttrList";
	}
	
	
	
	@RequestMapping("/getListJson")
	public void getListJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		
		String portName = request.getParameter("portName");
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		
		EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
		equipmentTypeAttr.setEquipmentTypeId(equipmentTypeId);
		equipmentTypeAttr.setPortName(portName);
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
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
        List<EquipmentTypeAttr> listTotal = this.service.selectByConditions(pu.getMap()); 
        List<EquipmentTypeAttr> allTotal = this.service.selects(equipmentTypeId); 
        
        Map map = new HashMap();
        map.put("total",allTotal.size());
        map.put("rows", listTotal);
        ResponseJsonUtil.responseToJson(response,map);
      
		
		
	}
	
	
	

}
