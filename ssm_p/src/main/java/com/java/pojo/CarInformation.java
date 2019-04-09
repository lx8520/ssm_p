package com.java.pojo;

import java.util.Date;

public class CarInformation {
	private Integer id;
	private String fileId;//档案编号
	private Date storageTime;//入场时间
	private String carId;//车牌号码
	private String identifierCode;//识别代码
	private String carType;//车辆类型
	private String carColor;//车辆颜色
	private String carBrand;//车辆品牌
	private String cost;//收费标准
	private String registrationName;//登记人
	private String park;//ͣ停车场
	private String lawEnforcement;//执法单位
	private String police;//办案民警
	private String note;//备注
	private String itemName;//物品名称
	private String specifications;//规格
	private String itemNum;//数量
	private String itemNote;//备注
	private String photo1;//整车照片1
	private String photo2;//整车照片2
	private String photo3;//整车照片3
	private String photo4;//整车照片4
	
	private String carState;//判断是否提交或入场
	private Integer State;//用于判断是否逾期
	private String auditNote;//审核人备注
	private Date leaveTime;//停车场离场时间
	private String storageName;//确认入场登记人
	private String leaveName;//确认离场登记人

	private Date createDateTime;//档案创建日期
	
	
	private Integer sub;//申请状态
	private String subDateTime;//申请创建日期
	

	public String getSubDateTime() {
		return subDateTime;
	}
	public void setSubDateTime(String subDateTime) {
		this.subDateTime = subDateTime;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public Integer getSub() {
		return sub;
	}
	public void setSub(Integer sub) {
		this.sub = sub;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreatDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getAuditNote() {
		return auditNote;
	}
	public void setAuditNote(String auditNote) {
		this.auditNote = auditNote;
	}
	public String getCarState() {
		return carState;
	}
	public void setCarState(String carState) {
		this.carState = carState;
	}

	
	public Integer getState() {
		return State;
	}
	public void setState(Integer state) {
		State = state;
	}

	public String getStorageName() {
		return storageName;
	}
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	
	
	private String Date;
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public Date getStorageTime() {
		return storageTime;
	}
	public void setStorageTime(Date storageTime) {
		this.storageTime = storageTime;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getIdentifierCode() {
		return identifierCode;
	}
	public void setIdentifierCode(String identifierCode) {
		this.identifierCode = identifierCode;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getRegistrationName() {
		return registrationName;
	}
	public void setRegistrationName(String registrationName) {
		this.registrationName = registrationName;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getLawEnforcement() {
		return lawEnforcement;
	}
	public void setLawEnforcement(String lawEnforcement) {
		this.lawEnforcement = lawEnforcement;
	}
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getItemNote() {
		return itemNote;
	}
	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getPhoto3() {
		return photo3;
	}
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}
	public String getPhoto4() {
		return photo4;
	}
	public void setPhoto4(String photo4) {
		this.photo4 = photo4;
	}
	
}
