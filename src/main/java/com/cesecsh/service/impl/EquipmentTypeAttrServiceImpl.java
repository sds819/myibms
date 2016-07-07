package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.service.EquipmentTypeAttrService;

@Service("/equipmentTypeAttrService")
public class EquipmentTypeAttrServiceImpl implements EquipmentTypeAttrService{

	@Resource
	private EquipmentTypeAttrService service;
	public int insert(EquipmentTypeAttr equipmentTypeAttr) {
		// TODO Auto-generated method stub
		return this.service.insert(equipmentTypeAttr);
	}

	public int update(EquipmentTypeAttr equipmentTypeAttr) {
		// TODO Auto-generated method stub
		return  this.service.update(equipmentTypeAttr);
	}

	public int delete(EquipmentTypeAttr equipmentTypeAttr) {
		// TODO Auto-generated method stub
		return this.service.delete(equipmentTypeAttr);
	}

	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return this.service.deletes(ids);
	}

	public EquipmentTypeAttr select(EquipmentTypeAttr equipmentTypeAttr) {
		// TODO Auto-generated method stub
		return this.service.select(equipmentTypeAttr);
	}

	public List<EquipmentTypeAttr> selects(String equipmentTypeId) {
		// TODO Auto-generated method stub
		return this.service.selects(equipmentTypeId);
	}

	public List<EquipmentTypeAttr> selectByConditions(Map map) {
		// TODO Auto-generated method stub
		return this.service.selectByConditions(map);
	}

}
