package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;


public interface EquipmentTypeService {
	
	
	public int insert(EquipmentType equipmentType);
	
	public int update(EquipmentType equipmentType);
	
	public int delete(EquipmentType equipmentType);
	
	public int deletes(String[] ids);
	
	public EquipmentType select(String equipmentTypeId); 
	
	public List<EquipmentType> selects();
	
	public List<EquipmentType> selectByConditions(Map map);
	

}
