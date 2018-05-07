package com.laponhcet.action.facekeeper;

import java.util.List;
import com.laponhcet.dao.FaceLogDAO;
import com.laponhcet.dto.FaceLogDTO;
import com.laponhcet.dto.UserRFIDDTO;
import com.laponhcet.util.FaceLogUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.Report;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class FaceLogListByDateFromDateToAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"", "", ""}, new String[] {"", "", ""}, new String[] {"", "", ""}, "U00020", "U00022");
		Pagination pagination = (Pagination) getSessionAttribute(FaceLogDTO.SESSION_FACELOG_PAGINATION);
		Report report = (Report) getSessionAttribute(Report.SESSION_REPORT);
		List<DTOBase> userRFIDList = (List<DTOBase>) getSessionAttribute(UserRFIDDTO.SESSION_USERRFID_LIST);
		
		report.setDateFrom(getRequestDateTime("txtDateFrom", "MM/dd/yyyy"));
		report.setDateTo(getRequestDateTime("txtDateTo", "MM/dd/yyyy"));

		List<DTOBase> faceLogList  = new FaceLogDAO().getFaceLogListByTimeLogFromAndTimeLogTo(report.getDateFrom(), report.getDateTo());
		List<DTOBase> presentUserRFIDList = FaceLogUtil.getPresentUserRFIDList(userRFIDList, faceLogList);

		pagination.setRecordListUnfiltered(presentUserRFIDList);
		pagination.setRecordList(presentUserRFIDList);

		FaceLogUtil.setPaginationRecord(pagination, faceLogList, report);
		setSessionAttribute(FaceLogDTO.SESSION_FACELOG_PAGINATION, pagination);
		
		setSessionAttribute(UserRFIDDTO.SESSION_USERRFID_LIST, userRFIDList);
		setSessionAttribute(FaceLogDTO.SESSION_FACELOG_LIST, faceLogList);
		setSessionAttribute(Report.SESSION_REPORT, report);
	}
}
