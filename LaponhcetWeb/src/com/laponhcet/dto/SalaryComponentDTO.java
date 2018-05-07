package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class SalaryComponentDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static String SESSION_SALARY_COMPONENT = "SESSION_SALARY_COMPONENT";
	public static String SESSION_SALARY_COMPONENT_lIST = "SESSION_SALARY_COMPONENT_lIST";
	
	private String name;
	private boolean isDeduction;
	
	public SalaryComponentDTO() {
		super();
		this.name = "";
		this.isDeduction = false;
	}

	public SalaryComponentDTO getSalaryDeduction() {
		SalaryComponentDTO salaryDeduction = new SalaryComponentDTO();
		salaryDeduction.setId(super.getId());
		salaryDeduction.setCode(super.getCode());
		salaryDeduction.setName(this.name);
		salaryDeduction.setDeduction(this.isDeduction);
		return salaryDeduction;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDeduction() {
		return isDeduction;
	}

	public void setDeduction(boolean isDeduction) {
		this.isDeduction = isDeduction;
	}
}
