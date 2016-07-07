package com.cesecsh.service;

import java.util.List;
import java.util.Map;

import com.cesecsh.model.Concentrator;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;


public interface ConcentratorService {
	
	
	public int insert(Concentrator concentrator);
	
	public int update(Concentrator concentrator);
	
	public int delete(Concentrator concentrator);
	
	public int deletes(String[] ids);
	
	public Concentrator select(String conId); 
	
	public List<Concentrator> selects();
	
	public List<Concentrator> selectByConditions(Map map);
	

}
