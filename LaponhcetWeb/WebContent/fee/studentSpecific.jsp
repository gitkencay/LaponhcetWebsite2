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
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Search Student Name/Code", "", "SearchValue", FeeStudentSpecificUtil.getStudentName(feeStudentSpecific.getStudent()), 40, TextBoxWebControl.DATA_TYPE_STRING, "onBlur=\"searchPagination(this.value); showTableStudent()\"") %>
<%
if(!StringUtil.isEmpty(feeStudentSpecific.getStudent().getCode())) {
%>
	<%=new LabelWebControl().getLabelWebControl(sessionInfo, "col-sm-3", "Academic Program", "AcademicProgram", feeStudentSpecific.getStudent().getAcademicProgram().getDisplayText(), "") %>
<%	
	if(StringUtil.isEmpty(feeStudentSpecific.getSemester().getCode())) {
%>
		<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-5", true, "Academic Year", "AcademicYear", academicYearList, feeStudentSpecific.getAcademicYear(), "", "", "") %>
<%			
	}
	else {
%>
		<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-5", true, "Semester", "Semester", semesterList, feeStudentSpecific.getSemester(), "", "", "") %>
<%		
	}
%>
	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", true, "Fee", "Fee", feeList, feeStudentSpecific.getFee(), "--Select Fee--", "0", "") %>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Amount", "Amount", "Amount", StringUtil.getFormattedNum(feeStudentSpecific.getAmount(), StringUtil.NUMERIC_STANDARD_FORMAT_NO_COMMA), 10, WebControlBase.DATA_TYPE_DOUBLE, "") %>
	<%//=new LabelWebControl().getLabelWebControl(sessionInfo, "col-sm-5", "&nbsp;", "AddFee", "<button class='btn btn-warning btn-circle' type='button'><i class='fa fa-times'></i></button>", "") %>
	<div id='divAddFee' class='col-sm-5'><br><label><b>&nbsp;</b></label><span class='m-t block'><i class='btn btn-warning btn-circle fa fa-times'></i>	</span></div>
<%
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