package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.ConcentratorParam;
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.service.ConcentratorParamService;
import com.cesecsh.service.ConcentratorTypeAttrService;
import com.cesecsh.service.EquipmentTypeAttrService;

@Service("/concentratorParamService")
public class ConcentratorParamServiceImpl implements ConcentratorParamService{

	@Resource
	private ConcentratorParamService service;

	public int insert(ConcentratorParam concentratorPram) {
		// TODO Auto-generated method stub
		return this.service.insert(concentratorPram);
	}

	public int update(ConcentratorParam concentratorPram) {
		// TODO Auto-generated method stub
		return this.service.update(concentratorPram);
	}

	public int delete(ConcentratorParam concentratorPram) {
		// TODO Auto-generated method stub
		return this.delete(concentratorPram);
	}

	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return this.deletes(ids);
	}

	public ConcentratorParam select(ConcentratorParam concentratorPram) {
		// TODO Auto-generated method stub
		return this.select(concentratorPram);
	}

	public List<ConcentratorParam> selects(String conId) {
		// TODO Auto-generated method stub
		return this.selects(conId);
	}

	public List<ConcentratorParam> selectByConditions(Map map) {
		// TODO Auto-generated method stub
		return this.selectByConditions(map);
	}

	
	
}
