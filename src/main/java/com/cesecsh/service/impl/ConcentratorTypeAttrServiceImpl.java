package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.service.ConcentratorTypeAttrService;
import com.cesecsh.service.EquipmentTypeAttrService;

@Service("/concentratorTypeAttrService")
public class ConcentratorTypeAttrServiceImpl implements ConcentratorTypeAttrService{

	@Resource
	private ConcentratorTypeAttrService service;

	public int insert(ConcentratorTypeAttr concentratorTypeAttr) {
		// TODO Auto-generated method stub
		return this.insert(concentratorTypeAttr);
	}

	public int update(ConcentratorTypeAttr concentratorTypeAttr) {
		// TODO Auto-generated method stub
		return this.update(concentratorTypeAttr);
	}

	public int delete(ConcentratorTypeAttr concentratorTypeAttr) {
		// TODO Auto-generated method stub
		return this.delete(concentratorTypeAttr);
	}

	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return this.deletes(ids);
	}

	public ConcentratorTypeAttr select(ConcentratorTypeAttr concentratorTypeAttr) {
		// TODO Auto-generated method stub
		return this.select(concentratorTypeAttr);
	}

	public List<ConcentratorTypeAttr> selects(String conTypeId) {
		// TODO Auto-generated method stub
		return this.selects(conTypeId);
	}

	public List<ConcentratorTypeAttr> selectByConditions(Map map) {
		// TODO Auto-generated method stub
		return this.selectByConditions(map);
	}

	
}
