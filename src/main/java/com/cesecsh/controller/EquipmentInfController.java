package com.cesecsh.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cesecsh.model.Concentrator;
import com.cesecsh.model.ConcentratorTypeAttr;
import com.cesecsh.model.Equipment;
import com.cesecsh.model.EquipmentInf;
import com.cesecsh.model.EquipmentInfType;
import com.cesecsh.model.EquipmentType;
import com.cesecsh.model.EquipmentTypeAttr;
import com.cesecsh.model.User;
import com.cesecsh.model.ValueType;
import com.cesecsh.service.ConcentratorService;
import com.cesecsh.service.EquipmentInfService;
import com.cesecsh.service.EquipmentService;
import com.cesecsh.service.EquipmentTypeAttrService;
import com.cesecsh.service.EquipmentTypeService;
import com.cesecsh.service.UserService;
import com.cesecsh.util.DbUtil;
import com.cesecsh.util.PageUtil;
import com.cesecsh.util.ResponseJsonUtil;


/**
 * 
 * 2016-07-06
 * @author Jesse 
 * 
 *
 */

@Controller
@RequestMapping("/equipmentInf")
public class EquipmentInfController {
	
	@Resource(name="equipmentInfService")
	private EquipmentInfService service;
	@Resource(name="equipmentTypeAttrService")
	private EquipmentTypeAttrService etas;
	@Resource(name="dataSource")
    private DataSource dataSource;
	@Resource(name="concentratorService")
	private ConcentratorService concentratorService;
	
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request,Model model){
		String equipmentInfId = request.getParameter("equipmentInfId");
		EquipmentInf ei = new EquipmentInf();
		ei.setEquipmentId(equipmentInfId);
		EquipmentInf equipmentInf = this.service.select(ei);
		model.addAttribute("equipmentInf",equipmentInf);
		return "equipment/showEquipmentInf";
	}
	
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response){
		
		String equipmentInfId = String.valueOf(System.currentTimeMillis());
		String equipmentInfName = request.getParameter("equipmentInfName");
		String unit = request.getParameter("unit");
		String prec = request.getParameter("prec");
		String upLimit = request.getParameter("upLimit");
		String lowLimit = request.getParameter("lowLimit");
	    String updateTime = request.getParameter("updateTime");
		String equipmentPartId = request.getParameter("equipmentPartId");
		String equipmentId = request.getParameter("equipmentId") ;
		String equipmentInfTypeId = request.getParameter("equipmentInfTypeId");//弃用
		String infCode = request.getParameter("infCode");
		Concentrator concentrator = new Concentrator();//conId--conId
		concentrator.setConId(request.getParameter("conId"));
		ConcentratorTypeAttr concentratorTypeAttr = new ConcentratorTypeAttr();//portId--portId
		concentratorTypeAttr.setPortId(request.getParameter("portId"));
		EquipmentInfType equipmentInfType =new EquipmentInfType();//equipmentInfType--typeValue
		equipmentInfType.setTypeValue(request.getParameter("equipmentInfType"));
		ValueType valueType = new ValueType();//valueType--code
		valueType.setCode(request.getParameter("valueType"));
		EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();//equipmentPortId--portId
		equipmentTypeAttr.setPortId(request.getParameter("equipmentPortId"));
		EquipmentInf equipmentInf = new EquipmentInf();
		equipmentInf.setConcentrator(concentrator);
		equipmentInf.setConcentratorTypeAttr(concentratorTypeAttr);
		equipmentInf.setEquipmentId(equipmentId);
		equipmentInf.setEquipmentInfId(equipmentInfId);
		equipmentInf.setEquipmentInfName(equipmentInfName);
		equipmentInf.setEquipmentInfType(equipmentInfType);
		equipmentInf.setEquipmentInfTypeId(equipmentInfTypeId);
		equipmentInf.setEquipmentPartId(equipmentPartId);
		equipmentInf.setEquipmentTypeAttr(equipmentTypeAttr);
		equipmentInf.setInfCode(infCode);
		equipmentInf.setLowLimit(lowLimit);
		equipmentInf.setPrec(prec);
		equipmentInf.setUnit(unit);
		equipmentInf.setUpdateTime(updateTime);
		equipmentInf.setUpLimit(upLimit);
		equipmentInf.setValueType(valueType);

		
		String flag="false";
		try{
			this.service.insert(equipmentInf);
			flag="success";
			
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	@RequestMapping("/update")
	public void update(HttpServletRequest request,HttpServletResponse response) throws IOException{

		String equipmentInfId =  request.getParameter("equipmentInfId");
		String equipmentInfName = request.getParameter("equipmentInfName");
		String unit = request.getParameter("unit");
		String prec = request.getParameter("prec");
		String upLimit = request.getParameter("upLimit");
		String lowLimit = request.getParameter("lowLimit");
	    String updateTime = request.getParameter("updateTime");
		String equipmentPartId = request.getParameter("equipmentPartId");
		String equipmentId = request.getParameter("equipmentId") ;
		String equipmentInfTypeId = request.getParameter("equipmentInfTypeId");//弃用
		String infCode = request.getParameter("infCode");
		Concentrator concentrator = new Concentrator();//conId--conId
		concentrator.setConId(request.getParameter("conId"));
		ConcentratorTypeAttr concentratorTypeAttr = new ConcentratorTypeAttr();//portId--portId
		concentratorTypeAttr.setPortId(request.getParameter("portId"));
		EquipmentInfType equipmentInfType =new EquipmentInfType();//equipmentInfType--typeValue
		equipmentInfType.setTypeValue(request.getParameter("equipmentInfType"));
		ValueType valueType = new ValueType();//valueType--code
		valueType.setCode(request.getParameter("valueType"));
		EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();//equipmentPortId--portId
		equipmentTypeAttr.setPortId(request.getParameter("equipmentPortId"));
		EquipmentInf equipmentInf = new EquipmentInf();
		equipmentInf.setConcentrator(concentrator);
		equipmentInf.setConcentratorTypeAttr(concentratorTypeAttr);
		equipmentInf.setEquipmentId(equipmentId);
		equipmentInf.setEquipmentInfId(equipmentInfId);
		equipmentInf.setEquipmentInfName(equipmentInfName);
		equipmentInf.setEquipmentInfType(equipmentInfType);
		equipmentInf.setEquipmentInfTypeId(equipmentInfTypeId);
		equipmentInf.setEquipmentPartId(equipmentPartId);
		equipmentInf.setEquipmentTypeAttr(equipmentTypeAttr);
		equipmentInf.setInfCode(infCode);
		equipmentInf.setLowLimit(lowLimit);
		equipmentInf.setPrec(prec);
		equipmentInf.setUnit(unit);
		equipmentInf.setUpdateTime(updateTime);
		equipmentInf.setUpLimit(upLimit);
		equipmentInf.setValueType(valueType);
		String flag="false";
		try{
			this.service.update(equipmentInf);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/destroy")
	public void destroy(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String equipmentInfId = request.getParameter("equipmentInfId");
		EquipmentInf equipmentInf = new EquipmentInf();
		equipmentInf.setEquipmentInfId(equipmentInfId);
		String flag="false";
		try{
			this.service.delete(equipmentInf);
			flag="success";
			ResponseJsonUtil.responseToJson(response,flag);
		
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping("/destroys")
	public void destoroys(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String selectedIds = request.getParameter("selectedIds");
		System.out.println("selectedIds:"+selectedIds);
		String[] ids = selectedIds.split(":");
		String flag="false";
		try{
			this.service.deletes(ids);
			flag="success";
			 ResponseJsonUtil.responseToJson(response,flag);
			
		}catch(Exception e){
			flag="false";
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request,Model model){
	
		String equipmentId = request.getParameter("equipmentId");
		String equipmentTypeId = request.getParameter("equipmentTypeId");
		model.addAttribute("equipmentId", equipmentId);
		model.addAttribute("equipmentTypeId", equipmentTypeId);
		return "equipment/showEquipmentInfList";
	}
	
	
	
	@RequestMapping("/getListJson")
	public void getListJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		String equipmentId = request.getParameter("equipmentId");
		String equipmentInfName = request.getParameter("equipmentInfName");
		String portName = request.getParameter("portName");
		
		EquipmentInf equipmentInf = new EquipmentInf();
		equipmentInf.setEquipmentId(equipmentId);
		equipmentInf.setEquipmentInfName(equipmentInfName);
		EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
		equipmentTypeAttr.setPortName(portName);
		equipmentInf.setEquipmentTypeAttr(equipmentTypeAttr);
    	List<String> sortColumns = new ArrayList<String>();
    	
    	sortColumns.add("equipment_inf_id");
    	sortColumns.add("equipment_inf_name");
    	
    	String sortFlag = "desc";
    	
    	PageUtil pu = PageUtil.getInstance();
    	pu.setObject(equipmentInf);
    	pu.setPage(page);
    	pu.setRows(rows);
    	pu.setSortFlag(sortFlag);
    	pu.setSortColumns(sortColumns);
    	System.out.println("map getListJson:"+pu.getMap());
        List<EquipmentInf> listTotal = this.service.selectByConditions(pu.getMap()); 
        List<EquipmentInf> allTotal = this.service.selects(equipmentId); 
        System.out.println("listTotal:"+listTotal.toArray());
        Map map = new HashMap();
        map.put("total",allTotal.size());
        map.put("rows", listTotal);
        ResponseJsonUtil.responseToJson(response,map);

	}
	
	
	@RequestMapping("/getEquipmentTypeAttrJson")
	public void getEquipmentTypeJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
				String equipmentTypeId=request.getParameter("equipmentTypeId");
				EquipmentTypeAttr equipmentTypeAttr = new EquipmentTypeAttr();
				List<EquipmentTypeAttr> list =  etas.selects(equipmentTypeId);
		    	
		        List arrayList = new ArrayList();
		        for(EquipmentTypeAttr e:list){
		        	 Map map = new HashMap();
		        	 map.put("id",e.getPortId());
		             map.put("name", e.getPortName());
		             arrayList.add(map);
		        }
		        ResponseJsonUtil.responseToJson(response,arrayList);

	}
	@RequestMapping("/getValueTypeJson")
	public void getValueTypeJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 以jdbc 代替mybatis
		 */
		
		String sql=" select id,code,name  from cec_value_type ";
    	JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
    	DataSource ds = jdbcTemplate.getDataSource();
    	Connection conn = null;
    	Statement st = null;
    	ResultSet rs = null;
    	List<ValueType> lists = new ArrayList();
		
    	try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				while(rs.next()){
					ValueType vt = new ValueType();
					vt.setId(rs.getString("id"));
					vt.setCode(rs.getString("code"));
					vt.setName(rs.getString("name"));
					lists.add(vt);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DbUtil.dbClose(conn, st, rs);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
    	List arrayList = new ArrayList();
        for(ValueType e:lists){
        	 Map map = new HashMap();
        	 map.put("id",e.getCode());
             map.put("name",e.getName());
             arrayList.add(map);
        }
        ResponseJsonUtil.responseToJson(response,arrayList);

	}

	
	
	@RequestMapping("/getEquipmentInfTypeJson")
	public void getEquipmentInfTypeJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
			
		/**
		 * 以jdbc 代替mybatis
		 */
		
		String sql=" select equipment_inf_type_id,code,state,type_value from cec_equipment_inf_type ";
    	JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
    	DataSource ds = jdbcTemplate.getDataSource();
    	Connection conn = null;
    	Statement st = null;
    	ResultSet rs = null;
    	List<EquipmentInfType> lists = new ArrayList();
		
    	try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				while(rs.next()){
					EquipmentInfType eif = new EquipmentInfType();
					eif.setEquipmentInfTypeId(rs.getString("equipment_inf_type_id"));
					eif.setCode(rs.getString("code"));
					eif.setState(rs.getString("state"));
					eif.setTypeValue(rs.getString("type_value"));
					lists.add(eif);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DbUtil.dbClose(conn, st, rs);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		}
		
    	List arrayList = new ArrayList();
        for(EquipmentInfType e:lists){
        	 Map map = new HashMap();
        	 map.put("id",e.getTypeValue());
             map.put("name",e.getCode());
             arrayList.add(map);
        }
        ResponseJsonUtil.responseToJson(response,arrayList);

	}
	
	@RequestMapping("/getConcentratorJson")
	public void getConcentratorJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		List<Concentrator> lists = concentratorService.selects();
		
    	List arrayList = new ArrayList();
        for(Concentrator e:lists){
        	 Map map = new HashMap();
        	 map.put("id",e.getConId());
             map.put("name",e.getConName());
             arrayList.add(map);
        }
        ResponseJsonUtil.responseToJson(response,arrayList);
	}
	
	@RequestMapping("/getConcentratorTypeAttrJson")
	public void getConcentratorTypeAttrJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		/**
		 * 以jdbc 代替mybatis
		 */
		String conId = request.getParameter("conId");
		String sql="SELECT PORT_ID port_id, PORT_NAME port_name FROM CEC_CONCENTRATOR_TYPE_ATTR AS A "
				+ "LEFT JOIN CEC_CONCENTRATOR_TYPE AS B ON A.CON_TYPE_ID = B.CON_TYPE_ID "
				+ "LEFT JOIN CEC_CONCENTRATOR AS C ON C.CON_TYPE_ID = A.CON_TYPE_ID "
				+ "WHERE C.CON_ID = '" + conId + "'";
    	JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
    	DataSource ds = jdbcTemplate.getDataSource();
    	Connection conn = null;
    	Statement st = null;
    	ResultSet rs = null;
    	List<ConcentratorTypeAttr> lists = new ArrayList();
		
    	try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				while(rs.next()){
					ConcentratorTypeAttr cta = new ConcentratorTypeAttr();
					cta.setPortId(rs.getString("port_id"));
					cta.setPortName(rs.getString("port_name"));
					
					lists.add(cta);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DbUtil.dbClose(conn, st, rs);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		}
		
    	List arrayList = new ArrayList();
        for(ConcentratorTypeAttr e:lists){
        	 Map map = new HashMap();
        	 map.put("id",e.getPortId());
             map.put("name",e.getPortName());
             arrayList.add(map);
        }
        ResponseJsonUtil.responseToJson(response,arrayList);

	}

	
	
	
	
	
	

}
