package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.Concentrator;
import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;


public interface ConcentratorTypeService {
	
	
	public int insert(ConcentratorType concentratorType);
	
	public int update(ConcentratorType concentratorType);
	
	public int delete(ConcentratorType concentratorType);
	
	public int deletes(String[] ids);
	
	public ConcentratorType select(String conId); 
	
	public List<ConcentratorType> selects();
	
	public List<ConcentratorType> selectByConditions(Map map);
	

}
