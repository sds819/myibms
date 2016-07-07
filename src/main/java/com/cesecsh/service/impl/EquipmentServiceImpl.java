package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;

@Service("/equipmentService")
public class EquipmentServiceImpl implements EquipmentService {

	@Resource
	private EquipmentService  service;//为什么此处就可以识别到是哪个注解？

	public int insert(Equipment equipment) {
		// TODO Auto-generated method stub
		return this.insert(equipment);
	}

	public int update(Equipment equipment) {
		// TODO Auto-generated method stub
		return this.update(equipment);
	}

	public int delete(Equipment equipment) {
		// TODO Auto-generated method stub
		return this.delete(equipment);
	}

	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return this.deletes(ids);
	}

	public Equipment select(String equipmentId) {
		// TODO Auto-generated method stub
		return this.select(equipmentId);
	}

	public List<Equipment> selects() {
		// TODO Auto-generated method stub
		return this.selects();
	}

	public List<Equipment> selectByConditions(Map map) {
		// TODO Auto-generated method stub
		return this.selectByConditions(map);
	}

	
}
