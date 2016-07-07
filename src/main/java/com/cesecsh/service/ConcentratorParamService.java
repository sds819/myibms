package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.Concentrator;
import com.cesecsh.model.ConcentratorParam;
import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;


public interface ConcentratorParamService {
	
	
	public int insert(ConcentratorParam concentratorPram);
	
	public int update(ConcentratorParam concentratorPram);
	
	public int delete(ConcentratorParam concentratorPram);
	
	public int deletes(String[] ids);
	
	public ConcentratorParam select(ConcentratorParam concentratorPram); 
	
	public List<ConcentratorParam> selects(String conId);
	
	public List<ConcentratorParam> selectByConditions(Map map);
	

}
