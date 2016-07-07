package com.cesecsh.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil {
	
	private  Map<Object,Object> map = new HashMap<Object,Object>();
	
	private List<String> sortColumns = new ArrayList<String>();
	
	private Object object = new Object();
	
	private Integer page = 1;
	
	private Integer rows = 10;
	
	private Integer start = 0;
	
	private Integer end = 10;
	
	private String sortFlag = "asc";
	
	private  static  PageUtil singleton = null;
	
	private PageUtil(){
		
	}
	
	public synchronized static PageUtil getInstance(){
		if(singleton==null){
			singleton =  new PageUtil();
		}
		return singleton;
	}
	

	public List<String> getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(List<String> sortColumns) {
		this.sortColumns = sortColumns;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Integer getStart() {
		return (this.getPage()-1)*this.getRows();
	}

	

	public Integer getEnd() {
		return this.getPage()*this.getRows();
	}

	

	public String getSortFlag() {
		return sortFlag;
	}

	public void setSortFlag(String sortFlag) {
		this.sortFlag = sortFlag;
	}

	

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Map<Object, Object> getMap() {
		map.put("object", this.getObject());
    	map.put("start", this.getStart());
    	map.put("end", this.getEnd());
    	map.put("sortColumns", this.getSortColumns());
    	map.put("sortFlag", this.getSortFlag());
		return map;
	}

	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}

	
	
	
	
	
	
	
	

}
