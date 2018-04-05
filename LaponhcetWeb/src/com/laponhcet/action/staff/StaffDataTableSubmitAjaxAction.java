package com.laponhcet.action.staff;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dto.StaffDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.StringUtil;

public class StaffDataTableSubmitAjaxAction extends AjaxActionBase{
private static final long serialVersionUID = 1L;


protected void searchRecord() {
	Pagination pagination = (Pagination) getSessionAttribute(StaffDTO.SESSION_STAFF_PAGINATION);
	String searchValue = getRequestString("txtSearchValue");
	if (StringUtil.isEmpty(searchValue)) {
		pagination.setRecordList(pagination.getRecordListUnfiltered());	
	}
	else{
		String searchCriteria = getRequestString("cboSearchCriteria");
		if(searchCriteria.equalsIgnoreCase(StaffDTO.STAFF_PAGINATION_SEARCH_CRITERIA_LIST[0])){
			pagination.setRecordList(new UserDAO().getUserListByUserGroupCodeSearchByNameCode(UserGroupDTO.USER_GROUP_STAFF_CODE,searchValue));
		}
	}
}
protected void setPaginationList() {
	Pagination pagination = (Pagination) getSessionAttribute(StaffDTO.SESSION_STAFF_PAGINATION);
	int currentPageTotalRecord = pagination.getCurrentPageRecordList().size();
	
	JSONArray jsonArray = new JSONArray();
	JSONObject jsonObj = new JSONObject();
	try {
		jsonObj.put("totalRecord", pagination.getRecordList().size());
		jsonObj.put("totalPage", pagination.getTotalPage());
		jsonObj.put("currentPage", pagination.getCurrentPage());
		jsonObj.put("recordPerPage", pagination.getRecordPerPage());
		jsonObj.put("currentPageTotalRecord", currentPageTotalRecord);
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	for(int i=0; i<currentPageTotalRecord; i++) {
		StaffDTO staff = (StaffDTO) pagination.getCurrentPageRecordList().get(i);
		try {
			JSONObject jsonObjDetails = new JSONObject();
			jsonObjDetails.put("pict", staff.getProfilePict());
			jsonObjDetails.put("code", staff.getCode());
			jsonObjDetails.put("name", staff.getName(true, true, true));
			jsonObjDetails.put("jobRole", staff.getJobRole());
			jsonObjDetails.put("assignedOffice", staff.getAssignedOffice());
			jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, pagination.getLinkButtonStr(sessionInfo, staff.getId()).replace("~", ","));
			jsonArray.put(jsonObjDetails);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	try {
		jsonObj.put("details", jsonArray);
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	try {
		response.getWriter().print(jsonObj);
		response.getWriter().flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
}