package com.cesecsh.model;

public class EquipmentInf {

	
	/**
	 * select a.EQUIPMENT_INF_ID,g.port_name,a.equipment_inf_type,a.value_type, a.UNIT,a.PREC,a.UP_LIMIT,a.LOW_LIMIT,
	 * a.UPDATE_TIME,a.CON_ID,b.CON_NAME,a.PORT_ID,c.PORT_NAME,a.INF_CODE,d.STATE,f.NAME,d.CODE,a.equipment_port_id  
	 * from CEC_EQUIPMENT_INF a left join CEC_CONCENTRATOR  B on a.CON_ID=b.CON_ID left join CEC_CONCENTRATOR_TYPE_ATTR c 
	 * on a.PORT_ID = c.PORT_ID left join CEC_EQUIPMENT_INF_TYPE d on a.EQUIPMENT_INF_TYPE=d.TYPE_VALUE left join 
	 * CEC_VALUE_TYPE f on f.code=a.value_type left join CEC_EQUIPMENT_TYPE_ATTR g on g.port_id=a.equipment_port_id 
	 */
	private String equipmentInfId;
	private String equipmentInfName;
	private String unit;
	private String prec;
	private String upLimit;
	private String lowLimit;
	private String updateTime;
	private String equipmentPartId;
	private String equipmentId;
	private String equipmentInfTypeId;//ÆúÓÃ
	private String infCode;
	private Concentrator concentrator;//conId--conId
	private ConcentratorTypeAttr concentratorTypeAttr;//portId--portId
	private EquipmentInfType equipmentInfType;//equipmentInfType--typeValue
	private ValueType valueType;//valueType--code
	private EquipmentTypeAttr equipmentTypeAttr;//equipmentPortId--portId
	public String getEquipmentInfId() {
		return equipmentInfId;
	}
	public void setEquipmentInfId(String equipmentInfId) {
		this.equipmentInfId = equipmentInfId;
	}
	public String getEquipmentInfName() {
		return equipmentInfName;
	}
	public void setEquipmentInfName(String equipmentInfName) {
		this.equipmentInfName = equipmentInfName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrec() {
		return prec;
	}
	public void setPrec(String prec) {
		this.prec = prec;
	}
	public String getUpLimit() {
		return upLimit;
	}
	public void setUpLimit(String upLimit) {
		this.upLimit = upLimit;
	}
	public String getLowLimit() {
		return lowLimit;
	}
	public void setLowLimit(String lowLimit) {
		this.lowLimit = lowLimit;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getEquipmentPartId() {
		return equipmentPartId;
	}
	public void setEquipmentPartId(String equipmentPartId) {
		this.equipmentPartId = equipmentPartId;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentInfTypeId() {
		return equipmentInfTypeId;
	}
	public void setEquipmentInfTypeId(String equipmentInfTypeId) {
		this.equipmentInfTypeId = equipmentInfTypeId;
	}
	public String getInfCode() {
		return infCode;
	}
	public void setInfCode(String infCode) {
		this.infCode = infCode;
	}
	public Concentrator getConcentrator() {
		return concentrator;
	}
	public void setConcentrator(Concentrator concentrator) {
		this.concentrator = concentrator;
	}
	public ConcentratorTypeAttr getConcentratorTypeAttr() {
		return concentratorTypeAttr;
	}
	public void setConcentratorTypeAttr(ConcentratorTypeAttr concentratorTypeAttr) {
		this.concentratorTypeAttr = concentratorTypeAttr;
	}
	public EquipmentInfType getEquipmentInfType() {
		return equipmentInfType;
	}
	public void setEquipmentInfType(EquipmentInfType equipmentInfType) {
		this.equipmentInfType = equipmentInfType;
	}
	public ValueType getValueType() {
		return valueType;
	}
	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}
	public EquipmentTypeAttr getEquipmentTypeAttr() {
		return equipmentTypeAttr;
	}
	public void setEquipmentTypeAttr(EquipmentTypeAttr equipmentTypeAttr) {
		this.equipmentTypeAttr = equipmentTypeAttr;
	}
	
	
}