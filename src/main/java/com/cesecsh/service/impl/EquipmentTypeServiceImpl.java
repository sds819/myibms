package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;

@Service("/equipmentTypeService")
public class EquipmentTypeServiceImpl implements EquipmentTypeService {

	@Resource
	private EquipmentTypeService  service;//为什么此处就可以识别到是哪个注解？

	public int insert(EquipmentType equipmentType) {
		// TODO Auto-generated method stub
		return this.service.insert(equipmentType);
	}

	public int update(EquipmentType equipmentType) {
		// TODO Auto-generated method stub
		return this.service.update(equipmentType);
	}

	public int delete(EquipmentType equipmentType) {
		// TODO Auto-generated method stub
		return this.service.delete(equipmentType);
	}

	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return this.service.deletes(ids);
	}

	public EquipmentType select(String id) {
		// TODO Auto-generated method stub
		return this.service.select(id);
	}

	public List<EquipmentType> selects() {
		// TODO Auto-generated method stub
		return this.service.selects();
	}

	public List<EquipmentType> selectByConditions(Map map) {
		// TODO Auto-generated method stub
		return this.service.selectByConditions(map);
	}
	
	
}
