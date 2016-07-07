package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.Concentrator;
import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;


public interface ConcentratorTypeAttrService {
	
	
	public int insert(ConcentratorTypeAttr concentratorTypeAttr);
	
	public int update(ConcentratorTypeAttr concentratorTypeAttr);
	
	public int delete(ConcentratorTypeAttr concentratorTypeAttr);
	
	public int deletes(String[] ids);
	
	public ConcentratorTypeAttr select(ConcentratorTypeAttr concentratorTypeAttr); 
	
	public List<ConcentratorTypeAttr> selects(String conTypeId);
	
	public List<ConcentratorTypeAttr> selectByConditions(Map map);
	

}
