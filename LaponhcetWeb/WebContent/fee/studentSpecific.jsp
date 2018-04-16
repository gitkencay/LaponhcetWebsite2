<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@	page import="com.laponhcet.dao.*"%>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	FeeStudentSpecificDTO feeStudentSpecific = (FeeStudentSpecificDTO) session.getAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC);
	List<DTOBase> feeStudentSpecificList = (List<DTOBase>) session.getAttribute(FeeStudentSpecificDTO.SESSION_FEESTUDENTSPECIFIC_LIST);
	Pagination studentPagination = (Pagination) session.getAttribute(StudentDTO.SESSION_STUDENT_PAGINATION);
	List<DTOBase> academicYearList = (List<DTOBase>) session.getAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
	List<DTOBase> semesterList = (List<DTOBase>) session.getAttribute(SemesterDTO.SESSION_SEMESTER_LIST);
	List<DTOBase> feeList = (List<DTOBase>) session.getAttribute(FeeDTO.SESSION_FEE_LIST);
%>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Search Student Name/Code", "", "SearchValue", StringUtil.isEmpty(feeStudentSpecific.getStudent().getCode())?studentPagination.getSearchValue():feeStudentSpecific.getStudent().getName(true, false, false), 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"searchPagination(this.value); showTableStudent()\"") %>
<%
if(!StringUtil.isEmpty(feeStudentSpecific.getStudent().getCode())) {
%>
	<%=new LabelWebControl().getLabelWebControl(sessionInfo, "col-sm-3", "Academic Program", "AcademicProgram", feeStudentSpecific.getStudent().getAcademicProgram().getDisplayText(), "") %>
<%	
	if(StringUtil.isEmpty(feeStudentSpecific.getSemester().getCode())) {
%>
		<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-5", true, "Academic Year", "AcademicYear", academicYearList, feeStudentSpecific.getAcademicYear(), "", "", "onchange=\"openLink('US0149')\"") %>
<%			
	}
	else {
%>
		<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-5", true, "Semester", "Semester", semesterList, feeStudentSpecific.getSemester(), "", "", "onchange=\"openLink('US0150')\"") %>
<%		
	}
%>
	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", true, "Fee", "Fee", feeList, feeStudentSpecific.getFee(), "--Select Fee--", "0", "") %>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", true, "Amount", "Amount", "Amount", StringUtil.getFormattedNum(feeStudentSpecific.getAmount(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 10, WebControlBase.DATA_TYPE_DOUBLE, "") %>
	<div id='divAddFee' class='col-sm-1'><br><label><b>&nbsp;</b></label><br><i class='btn btn-primary fa fa-plus-circle' onclick="openLink('US0173')"></i>	</div>
<%
	if(feeStudentSpecificList.size() >= 1) {
%>	
	<div class="col-sm-6">
		<br><br>
		<%=WebUtil.getTable("table table-bordered", FeeStudentSpecificUtil.getFeeStudentSpecificListArr(feeStudentSpecificList, 3), 3) %>
	</div>			
<%
	}
}
%>

<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-t-lg", "btn btn-primary", "align='center'") %>

<div class="modal inmodal fade" id="tblStudent" tabindex="-1" role="dialog"  aria-hidden="true" style="display: none; left: auto; right: 10px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
        	<%=new PaginationWebControl().getPaginationWebControlList(sessionInfo, "col-sm-12", studentPagination, feeStudentSpecific.getStudent().getId(), "US0151", true) %>
        </div>
    </div>
</div> 

<script>
	function showTableStudent() {
		if(document.getElementById("txtSearchValue").value!="") {
			$("#tblStudent").modal();
		}
	}
</script>