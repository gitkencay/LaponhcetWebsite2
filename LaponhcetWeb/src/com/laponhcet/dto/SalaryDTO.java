package com.laponhcet.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DateTimeUtil;

public class SalaryDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	public static String SESSION_SALARY = "SESSION_SALARY";
	public static String SESSION_SALARY_lIST = "SESSION_SALARY_lIST";
	
	public static final String SALARY_TYPE_DAILY = "DAILY";
	public static final String SALARY_TYPE_WEEKLY = "WEEKLY";
	public static final String SALARY_TYPE_BI_MONTHLY = "BI-MONTHLY";
	public static final String SALARY_TYPE_MONTHLY = "MONTHLY";
	
	private StaffDTO staff;
	private String salaryType;
	private Date periodStartDate;
	private Date periodEndDate;
	private List<DTOBase> salaryComponent;
	
	public SalaryDTO() {
		super();
		this.staff = new StaffDTO();
		this.salaryType = SALARY_TYPE_WEEKLY;
		this.periodStartDate = DateTimeUtil.addDay(DateTimeUtil.getCurrentTimestamp(), -7);
		this.periodEndDate = DateTimeUtil.getCurrentTimestamp();
		this.salaryComponent = new ArrayList<DTOBase>();
	}
	
	public SalaryDTO getSalary() {
		SalaryDTO salary = new SalaryDTO();
		salary.setId(super.getId());
		salary.setCode(super.getCode());
		salary.setStaff(this.staff);
		salary.setSalaryType(this.salaryType);
		salary.setPeriodStartDate(this.periodStartDate);
		salary.setPeriodEndDate(this.periodEndDate);
		salary.setSalaryComponent(this.salaryComponent);
		return salary;
	}
	
	public StaffDTO getStaff() {
		return staff;
	}

	public void setStaff(StaffDTO staff) {
		this.staff = staff;
	}

	public String getSalaryType() {
		return salaryType;
	}
	
	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}
	
	public Date getPeriodStartDate() {
		return periodStartDate;
	}
	
	public void setPeriodStartDate(Date periodStartDate) {
		this.periodStartDate = periodStartDate;
	}
	
	public Date getPeriodEndDate() {
		return periodEndDate;
	}
	
	public void setPeriodEndDate(Date periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	public List<DTOBase> getSalaryComponent() {
		return salaryComponent;
	}

	public void setSalaryComponent(List<DTOBase> salaryComponent) {
		this.salaryComponent = salaryComponent;
	}
}
