package com.cesecsh.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesecsh.model.Concentrator;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.User;
import com.cesecsh.service.ConcentratorService;
import com.cesecsh.service.EquipmentService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;

@Service("/concentratorService")
public class ConcentratorServiceImpl implements ConcentratorService {

	@Resource
	private ConcentratorService  service;

	public int insert(Concentrator concentrator) {
		// TODO Auto-generated method stub
		return this.insert(concentrator);
	}

	public int update(Concentrator concentrator) {
		// TODO Auto-generated method stub
		return this.update(concentrator);
	}

	public int delete(Concentrator concentrator) {
		// TODO Auto-generated method stub
		return this.delete(concentrator);
	}

	public int deletes(String[] ids) {
		// TODO Auto-generated method stub
		return this.deletes(ids);
	}

	public Concentrator select(String conId) {
		// TODO Auto-generated method stub
		return this.select(conId);
	}

	public List<Concentrator> selects() {
		// TODO Auto-generated method stub
		return this.selects();
	}

	public List<Concentrator> selectByConditions(Map map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
