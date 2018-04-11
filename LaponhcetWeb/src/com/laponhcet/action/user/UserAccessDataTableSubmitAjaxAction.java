package com.laponhcet.action.user;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.util.UserAccessUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.StringUtil;

public class UserAccessDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(UserDTO.SESSION_USER_PAGINATION);
		String searchValue = getRequestString("txtSearchValue");
		
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else {
			String searchCriteria = getRequestString("cboSearchCriteria");
			if(searchCriteria.equalsIgnoreCase(UserAccessUtil.PAGINATION_SEARCH_CRITERIA_LIST[0])) {
				pagination.setRecordList(new UserDAO().getUserListSearchByNameCode(searchValue));
			}
		}
	}
	
	protected void setPaginationList() {
		Pagination pagination = (Pagination) getSessionAttribute(UserDTO.SESSION_USER_PAGINATION);
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
			UserDTO user = (UserDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("id", user.getCode());
				jsonObjDetails.put("lastName", user.getLastName());
				jsonObjDetails.put("firstName", user.getFirstName());
				jsonObjDetails.put("middleName", user.getMiddleName());
				jsonObjDetails.put("group", user.getUserGroupCodes());
				//jsonObjDetails.put("button", pagination.getRecordButtonStr(sessionInfo, user.getId()).replace("~", ","));
				jsonObjDetails.put("button", UserAccessUtil.getRecordButtonStr(sessionInfo, user));
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

