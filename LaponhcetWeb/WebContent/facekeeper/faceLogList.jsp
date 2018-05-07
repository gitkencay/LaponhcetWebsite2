<%@page import="com.laponhcet.dao.FaceLogDAO"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mytechnopal.*"%>
<%@ page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.webcontrol.*"%>
<%@ page import="com.mytechnopal.util.*"%>
<%@ page import="com.mytechnopal.dto.*"%>
<%@ page import="com.mytechnopal.dao.*"%>
<%@ page import="com.laponhcet.dto.*"%>
<%@ page import="com.laponhcet.util.*"%>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	Pagination pagination = (Pagination) session.getAttribute(FaceLogDTO.SESSION_FACELOG_PAGINATION);
	Report report = (Report) session.getAttribute(Report.SESSION_REPORT);
%>

<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-2", false, "Show Entries", "RecordPerPage", new String[]{"10", "20", "50", "100"}, "10", new String[]{"10", "20", "50", "100"}, "", "", "onChange=\"showPagination('" + Pagination.PAGINATION_ACTION_lIMIT + "', this.value);\"")%>
<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-2", false, "Search Criteria", "SearchCriteria", FaceLogDTO.PAGINATION_SEARCH_CRITERIA_LIST, FaceLogDTO.PAGINATION_SEARCH_CRITERIA_LIST[0], FaceLogDTO.PAGINATION_SEARCH_CRITERIA_LIST, "", "","")%>
<%=new TextBoxWebControl().getTextBoxWebControl(null, "col-sm-3", false, "Search Value", "","SearchValue", "", 40, TextBoxWebControl.DATA_TYPE_STRING,"onBlur=\"searchPagination(this.value);\"")%>
<%=new ComboBoxWebControl().getComboBoxWebControl(null, "col-sm-2", false, "Period", "Period", FaceLogDTO.PAGINATION_PERIOD_CRITERIA_LIST, report.getReportDate(), FaceLogDTO.PAGINATION_PERIOD_CRITERIA_LIST, "", "", "onChange=\"getPeriodSpecificInput(this.value)\"")%>
<div id='divReportDateByDay'>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3 m-b", false, "Choose Day", "Day", "Date", DateTimeUtil.getDateTimeToStr(report.getDateFrom(), "MM/dd/yyyy"), 10,TextBoxWebControl.DATA_TYPE_DATE, "onChange=\"openLink('U00019');\"", "maxDate: '-24M', changeMonth: true, changeYear: true")%>
</div>
<div id='divReportDateFromTo'>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3 m-b", false, "From", "From", "DateFrom", DateTimeUtil.getDateTimeToStr(report.getDateFrom(), "MM/dd/yyyy"), 10,TextBoxWebControl.DATA_TYPE_DATE, "")%>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3 m-b", false, "To", "To", "DateTo", DateTimeUtil.getDateTimeToStr(report.getDateTo(), "MM/dd/yyyy"), 10,TextBoxWebControl.DATA_TYPE_DATE, "onChange=\"openLink('U00020');\"", "maxDate: '-24M', changeMonth: true, changeYear: true")%>
</div>
<div class="col-sm-12 m-t">
	<%=new PaginationWebControl().getPaginationWebControlGrid(sessionInfo, "col-sm-12", pagination, 0, "col-sm-4") %>
</div>