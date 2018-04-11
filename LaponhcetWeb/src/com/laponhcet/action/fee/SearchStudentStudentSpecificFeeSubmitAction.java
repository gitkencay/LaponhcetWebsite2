package com.laponhcet.action.fee;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.util.StudentUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserGroupDTO;
import com.mytechnopal.util.StringUtil;

public class SearchStudentStudentSpecificFeeSubmitAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
 		pagination.setSearchValue(getRequestString("txtSearchValue"));
		if(StringUtil.isEmpty(pagination.getSearchValue())) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			if(pagination.getSearchCriteria().equalsIgnoreCase(StudentDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				List<DTOBase> userListByCodeName = new UserDAO().getUserListByUserGroupCodeSearchByNameCode(UserGroupDTO.USER_GROUP_STUDENT_CODE, pagination.getSearchValue());
				pagination.setRecordList(StudentUtil.getStudentListByUserList(pagination, userListByCodeName, (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST)));
			}
		}
	}
	
	protected void setPaginationList() {
		Pagination pagination = (Pagination) getSessionAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
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
			StudentDTO student = (StudentDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_ID, student.getId());
				jsonObjDetails.put("id", student.getCode());
				jsonObjDetails.put("last_name", student.getLastName());
				jsonObjDetails.put("first_name", student.getFirstName());
				jsonObjDetails.put("middle_initial", StringUtil.isEmpty(student.getMiddleName())?"":StringUtil.getLeft(student.getMiddleName(), 1));
				jsonObjDetails.put("academic_program", student.getAcademicProgram().getDisplayText());
				jsonObjDetails.put(Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON, "<a href='#' onClick=\"recordAction(" + student.getId() + ", 'US0151')\">Select</a>");
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
