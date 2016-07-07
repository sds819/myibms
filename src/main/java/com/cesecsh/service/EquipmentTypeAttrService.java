package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.model.User;


public interface EquipmentTypeAttrService {
	
	
	public int insert(EquipmentTypeAttr equipmentTypeAttr);
	
	public int update(EquipmentTypeAttr equipmentTypeAttr);
	
	public int delete(EquipmentTypeAttr equipmentTypeAttr);
	
	public int deletes(String[] ids);
	
	public EquipmentTypeAttr select(EquipmentTypeAttr equipmentTypeAttr); 
	
	public List<EquipmentTypeAttr> selects(String equipmentTypeId);
	
	public List<EquipmentTypeAttr> selectByConditions(Map map);
	

}
