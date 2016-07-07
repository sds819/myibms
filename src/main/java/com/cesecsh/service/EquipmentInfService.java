package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.Concentrator;
import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentInf;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;


public interface EquipmentInfService {
	
	
	public int insert(EquipmentInf equipmentInf);
	
	public int update(EquipmentInf equipmentInf);
	
	public int delete(EquipmentInf equipmentInf);
	
	public int deletes(String[] ids);
	
	public EquipmentInf select(EquipmentInf equipmentInf); 
	
	public List<EquipmentInf> selects(String equipmentId);
	
	public List<EquipmentInf> selectByConditions(Map map);
	

}
