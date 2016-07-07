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
import com.cesecsh.model.ConcentratorParam;
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorParamService;
import com.cesecsh.service.ConcentratorTypeAttrService;
import com.cesecsh.service.ConcentratorTypeService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.EquipmentTypeAttrService;
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
@RequestMapping("/concentratorParam")
public class ConcentratorParamController {
	
	@Resource(name="concentratorParamService")
	private ConcentratorParamService service;
	

	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,Model model){
		ConcentratorParam cp=new  ConcentratorParam();
		String conId = request.getParameter("conId");
		cp.setConId(conId);
		
		
		ConcentratorParam concentratorParam = this.service.select(cp);
		model.addAttribute("concentratorParam",concentratorParam);
		return "concentrator/showConcentratorParam";
	}
	
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		
		String conParamId = String.valueOf(System.currentTimeMillis());
		String conParam = request.getParameter("conParam");
		String conValue = request.getParameter("conValue");
		String conId = request.getParameter("conId");
	
		ConcentratorParam concentratorParam = new ConcentratorParam();
		concentratorParam.setConId(conId);
		concentratorParam.setConParam(conParam);
		concentratorParam.setConParamId(conParamId);
		concentratorParam.setConValue(conValue);
		
		
		String flag="false";
		try{
			this.service.insert(concentratorParam);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String conParamId =  request.getParameter("conParamId");
		String conParam = request.getParameter("conParam");
		String conValue = request.getParameter("conValue");
		String conId = request.getParameter("conId");
	
		ConcentratorParam concentratorParam = new ConcentratorParam();
		concentratorParam.setConId(conId);
		concentratorParam.setConParam(conParam);
		concentratorParam.setConParamId(conParamId);
		concentratorParam.setConValue(conValue);
		
		
		String flag="false";
		try{
			this.service.update(concentratorParam);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/destroy")
	public void destroy(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String conParamId = request.getParameter("conParamId");
		ConcentratorParam concentratorParam = new ConcentratorParam();
		concentratorParam.setConParamId(conParamId);
		String flag="false";
		try{
			this.service.delete(concentratorParam);
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
	
		String conId = request.getParameter("conId");
		model.addAttribute("conId",conId);
		return "concentrator/showConcentratorParamList";
	
	}
	
	
	
	@RequestMapping("/getListJson")
	public void getListJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
	
		String conParam = request.getParameter("conParam");
		String conId = request.getParameter("conId");
	
		
		
		ConcentratorParam concentratorParam = new ConcentratorParam();
		concentratorParam.setConParam(conParam);
		concentratorParam.setConId(conId);

		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
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
        List<ConcentratorParam> allTotal = this.service.selects(conId); 
        
        Map map = new HashMap();
        map.put("total",allTotal.size());
        map.put("rows", listTotal);
        ResponseJsonUtil.responseToJson(response,map);
      
		
		
	}
	
	
	

}
