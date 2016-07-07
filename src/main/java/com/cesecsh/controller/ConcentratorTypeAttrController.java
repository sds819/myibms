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
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorTypeAttrService;
import com.cesecsh.service.ConcentratorTypeService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.EquipmentTypeAttrService;
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
@RequestMapping("/concentratorTypeAttr")
public class ConcentratorTypeAttrController {
	
	@Resource(name="concentratorTypeAttrService")
	private ConcentratorTypeAttrService service;
	

	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,Model model){
		ConcentratorTypeAttr cta=new  ConcentratorTypeAttr();
		String conTypeId = request.getParameter("conTypeId");
		cta.setConTypeId(conTypeId);
		
		ConcentratorTypeAttr concentratorTypeAttr = this.service.select(cta);
		model.addAttribute("concentratorTypeAttr",concentratorTypeAttr);
		return "concentratorType/showConcentratorTypeAttr";
	}
	
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		
		String portId = String.valueOf(System.currentTimeMillis());
		String portName = request.getParameter("portName");
		String portCode = request.getParameter("portCode");
		String conTypeId = request.getParameter("conTypeId");
	
		ConcentratorTypeAttr concentratorTypeAttr = new ConcentratorTypeAttr();
		concentratorTypeAttr.setConTypeId(conTypeId);
		concentratorTypeAttr.setPortCode(portCode);
		concentratorTypeAttr.setPortCode(portCode);
		concentratorTypeAttr.setPortName(portName);
		
		String flag="false";
		try{
			this.service.insert(concentratorTypeAttr);
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
		String portName = request.getParameter("portName");
		String portCode = request.getParameter("portCode");
		String conTypeId = request.getParameter("conTypeId");
	
		ConcentratorTypeAttr concentratorTypeAttr = new ConcentratorTypeAttr();
		concentratorTypeAttr.setPortId(portId);
		concentratorTypeAttr.setPortCode(portCode);
		concentratorTypeAttr.setConTypeId(conTypeId);
		concentratorTypeAttr.setPortName(portName);
		String flag="false";
		try{
			this.service.update(concentratorTypeAttr);
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
		ConcentratorTypeAttr concentratorTypeAttr = new ConcentratorTypeAttr();
		concentratorTypeAttr.setPortId(portId);
		String flag="false";
		try{
			this.service.delete(concentratorTypeAttr);
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
	
		String conTypeId = request.getParameter("conTypeId");
		model.addAttribute("conTypeId",conTypeId);
		return "concentratorType/showConcentratorTypeAttrList";
	
	}
	
	
	
	@RequestMapping("/getListJson")
	public void getListJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		
		String portName = request.getParameter("portName");
		String conTypeId = request.getParameter("conTypeId");
		
		ConcentratorTypeAttr concentratorTypeAttr = new ConcentratorTypeAttr();
		concentratorTypeAttr.setConTypeId(conTypeId);
		concentratorTypeAttr.setPortName(portName);
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
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
        List<ConcentratorTypeAttr> listTotal = this.service.selectByConditions(pu.getMap()); 
        List<ConcentratorTypeAttr> allTotal = this.service.selects(conTypeId); 
        
        Map map = new HashMap();
        map.put("total",allTotal.size());
        map.put("rows", listTotal);
        ResponseJsonUtil.responseToJson(response,map);
      
		
		
	}
	
	
	

}
