package com.laponhcet.action.staff;

import java.util.List;

import com.laponhcet.dao.StaffDAO;
import com.laponhcet.dto.StaffDTO;
import com.laponhcet.util.StaffUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dao.ReligionDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.ReligionDTO;

public class ListStaffAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0097", "US0079", "US0084" }, new String[] { "US0080", "US0085", "US0087" }, new String[] { "US0081", "US0086", "US0088" }, "US0082", "US0083");
		Pagination pagination = null;
		if(!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit()) {
			pagination = new Pagination();
				List<DTOBase> cityList = (List<DTOBase>) new CityDAO().getCityList();
				List<DTOBase> religionList = (List<DTOBase>) new ReligionDAO().getReligionList();
				List<DTOBase> staffList = new StaffDAO().getStaffList();
				pagination.setName(StaffDTO.SESSION_STAFF_PAGINATION);
				pagination.setSearchCriteria(StaffDTO.STAFF_PAGINATION_SEARCH_CRITERIA_LIST[0]);
				pagination.setColumnNameList(new String[] {"Profile Pict", "Code", "Name", "Job Role", "Office Assigned", ""});
				pagination.setColumnWidthList(new String[] { "20", "10", "20", "15", "15", "20"});	
				pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
				pagination.setRecordListUnfiltered(staffList);
				pagination.setRecordList(staffList);
				pagination.setAjaxResultDetailsList(new String[] {"pict", "code", "name", "jobRole", "officeAssigned", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
				
				setSessionAttribute(StaffDTO.SESSION_STAFF_PAGINATION, pagination);
				setSessionAttribute(CityDTO.SESSION_CITY_LIST, cityList);
				setSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST, religionList);
				setSessionAttribute(StaffDTO.SESSION_STAFF, new StaffDTO());
			}
			else{
				pagination = (Pagination) getSessionAttribute(StaffDTO.SESSION_STAFF_PAGINATION);
			}
			StaffUtil.setPaginationRecord(sessionInfo, pagination);
			setSessionAttribute(StaffDTO.SESSION_STAFF, new StaffDTO());
		}

}
