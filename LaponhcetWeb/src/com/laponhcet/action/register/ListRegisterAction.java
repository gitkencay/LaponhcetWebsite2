package com.laponhcet.action.register;

import java.util.List;

import com.laponhcet.dao.RegisterDAO;
import com.laponhcet.dto.RegisterDTO;
import com.laponhcet.util.RegisterUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class ListRegisterAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"UE0012", "UE0002", "UE0007"}, new String[] {"UE0003", "UE0008", "UE0010"}, new String[] {"UE0004", "UE0009", "UE0011"}, "UE0005", "UE0006");
		Pagination pagination = null;
		if((!sessionInfo.isPreviousLinkView() && !sessionInfo.isPreviousLinkUpdate() && !sessionInfo.isPreviousLinkDeleteSubmit() && !sessionInfo.isPreviousLinkUpdateConfirm())) {
			pagination = new Pagination();
			List<DTOBase> registerList = new RegisterDAO().getRegisterList();
			pagination.setName(RegisterDTO.SESSION_REGISTER_PAGINATION);
			pagination.setSearchCriteria(RegisterDTO.PAGINATION_SEARCH_CRITERIA_LIST[0]);
			pagination.setColumnNameList(new String[] {"Last Name","First Name", "Middle Name", "Status", ""});
			pagination.setColumnWidthList(new String[] {"22","22", "22", "11", "23"});	
			pagination.setAjaxLinkCode(sessionInfo.getPaginationLink().getCode());
			pagination.setRecordListUnfiltered(registerList);
			pagination.setRecordList(registerList);
			pagination.setAjaxResultDetailsList(new String[] {"lastName", "firstName", "middleName", "status", Pagination.PAGINATION_TABLE_ROW_LINK_BUTTON});
			setSessionAttribute(RegisterDTO.SESSION_REGISTER, new RegisterDTO());
		}
		else {
			pagination = (Pagination) getSessionAttribute(RegisterDTO.SESSION_REGISTER_PAGINATION);
		}
		RegisterUtil.setPaginationRecord(sessionInfo, pagination);
		setSessionAttribute(pagination.getName(), pagination);
	}
}