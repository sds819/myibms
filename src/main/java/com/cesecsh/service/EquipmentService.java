package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;


public interface EquipmentService {
	
	
	public int insert(Equipment equipment);
	
	public int update(Equipment equipment);
	
	public int delete(Equipment equipment);
	
	public int deletes(String[] ids);
	
	public Equipment select(String equipmentId); 
	
	public List<Equipment> selects();
	
	public List<Equipment> selectByConditions(Map map);
	

}
