package com.laponhcet.action.facekeeper;

import java.util.List;
import com.laponhcet.dao.FaceLogDAO;
import com.laponhcet.dao.UserRFIDDAO;
import com.laponhcet.dto.FaceLogDTO;
import com.laponhcet.dto.UserRFIDDTO;
import com.laponhcet.util.FaceLogUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.Report;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class FaceLogListByDateAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		Pagination pagination = null;
		Report report = null;
		List<DTOBase> userRFIDList = null;
		if(sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("U00019") || sessionInfo.getPreviousLink().getCode().equalsIgnoreCase("U00020")) {
			pagination = (Pagination) getSessionAttribute(FaceLogDTO.SESSION_FACELOG_PAGINATION);
			report = (Report) getSessionAttribute(Report.SESSION_REPORT);
			report.setDateFrom(getRequestDateTime("txtDate", "MM/dd/yyyy"));
			report.setDateTo(getRequestDateTime("txtDate", "MM/dd/yyyy"));
			userRFIDList = (List<DTOBase>) getSessionAttribute(UserRFIDDTO.SESSION_USERRFID_LIST);
		}
		else {
			sessionInfo.setTransitionLink(new String[] {"", "", ""}, new String[] {"", "", ""}, new String[] {"", "", ""}, "U00019", "U00022");
			pagination = new Pagination();
			report = new Report();
			userRFIDList = new UserRFIDDAO().getUserRFIDList();
		}
		List<DTOBase> faceLogList  = new FaceLogDAO().getFaceLogListByTimeLogFromAndTimeLogTo(report.getDateFrom(), report.getDateTo());
		List<DTOBase> presentUserRFIDList = FaceLogUtil.getPresentUserRFIDList(userRFIDList, faceLogList);

		pagination.setRecordListUnfiltered(presentUserRFIDList);
		pagination.setRecordList(presentUserRFIDList);

		FaceLogUtil.setPaginationRecord(pagination, faceLogList);
		setSessionAttribute(FaceLogDTO.SESSION_FACELOG_PAGINATION, pagination);
		
		setSessionAttribute(UserRFIDDTO.SESSION_USERRFID_LIST, userRFIDList);
		setSessionAttribute(FaceLogDTO.SESSION_FACELOG_LIST, faceLogList);
		setSessionAttribute(Report.SESSION_REPORT, report);
	}
}
