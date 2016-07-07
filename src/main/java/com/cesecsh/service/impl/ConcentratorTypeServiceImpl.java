package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.ConcentratorType;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorTypeService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;

@Service("/concentratorTypeService")
public class ConcentratorTypeServiceImpl implements ConcentratorTypeService {

	@Resource
	private ConcentratorTypeService  service;

	public int insert(ConcentratorType concentratorType) {
		// TODO Auto-generated method stub
		return this.insert(concentratorType);
	}

	public int update(ConcentratorType concentratorType) {
		// TODO Auto-generated method stub
		return this.update(concentratorType);
	}

	public int delete(ConcentratorType concentratorType) {
		// TODO Auto-generated method stub
		return this.delete(concentratorType);
	}

	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return this.deletes(ids);
	}

	public ConcentratorType select(String conId) {
		// TODO Auto-generated method stub
		return this.select(conId);
	}

	public List<ConcentratorType> selects() {
		// TODO Auto-generated method stub
		return this.selects();
	}

	public List<ConcentratorType> selectByConditions(Map map) {
		// TODO Auto-generated method stub
		return this.selectByConditions(map);
	}

	
	
}
