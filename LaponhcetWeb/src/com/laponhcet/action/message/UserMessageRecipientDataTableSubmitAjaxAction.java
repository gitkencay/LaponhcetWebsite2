package com.laponhcet.action.message;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.util.MessageUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.StringUtil;

public class UserMessageRecipientDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(UserDTO.SESSION_USER_PAGINATION);
		String searchValue = getRequestString("txtSearchValue");
		if(StringUtil.isEmpty(searchValue)) {
			pagination.setRecordList(pagination.getRecordListUnfiltered());
		}
		else{
			pagination.setRecordList(new UserDAO().getUserListSearchByNameCode(searchValue));
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
				jsonObjDetails.put("last_name", user.getLastName());
				jsonObjDetails.put("first_name", user.getFirstName());
				jsonObjDetails.put("middle_name", user.getMiddleName());
				jsonObjDetails.put("cp_number", user.getCpNumber());
				jsonObjDetails.put("button", MessageUtil.getAddRecipientButton(user.getCode()));
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

