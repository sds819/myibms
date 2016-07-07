package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentInf;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentInfService;
import com.cesecsh.service.EquipmentService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;

@Service("/equipmentInfService")
public class EquipmentInfServiceImpl implements EquipmentInfService {

	@Resource
	private EquipmentInfService  service;

	public int insert(EquipmentInf equipmentInf) {
		// TODO Auto-generated method stub
		return this.insert(equipmentInf);
	}

	public int update(EquipmentInf equipmentInf) {
		// TODO Auto-generated method stub
		return this.update(equipmentInf);
	}

	public int delete(EquipmentInf equipmentInf) {
		// TODO Auto-generated method stub
		return this.delete(equipmentInf);
	}

	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return this.deletes(ids);
	}

	public EquipmentInf select(EquipmentInf equipmentInf) {
		// TODO Auto-generated method stub
		return this.select(equipmentInf);
	}

	public List<EquipmentInf> selects(String equipmentId) {
		// TODO Auto-generated method stub
		return this.selects(equipmentId);
	}

	public List<EquipmentInf> selectByConditions(Map map) {
		// TODO Auto-generated method stub
		return this.selectByConditions(map);
	}

	
	
}
