<%@ page import="com.mytechnopal.base.*"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %> 
<%@ page import="com.laponhcet.dao.*" %> 
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="java.util.*" %>
<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	FeeDTO fee = (FeeDTO) session.getAttribute(FeeDTO.SESSION_FEE);
	FeeDTO feeParent = (FeeDTO) session.getAttribute(FeeDTO.SESSION_FEE + "_PARENT");
	List<DTOBase> feeParentList = (ArrayList<DTOBase>)session.getAttribute(FeeDTO.SESSION_FEE_LIST);
	List<DTOBase> academicProgramList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
	List<DTOBase> academicProgramGroupList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST);
%>
<div class="col-lg-12 m-t-lg">
	<%=new CheckBoxWebControl().getCheckBoxWebControl(sessionInfo, "col-sm-2", false, "Mandatory", fee.isMandatory(), "Mandatory", "") %>
    <%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Parent", "Parent", feeParentList, feeParent, "--NONE--", "", "onchange=\"openLink('U00053')\"") %>
    <%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Name", "Name", "Name", fee.getName(), 20, WebControlBase.DATA_TYPE_STRING, "")%>
    <div class="row">
		<%
			for(DTOBase obj: academicProgramGroupList) {
				AcademicProgramGroupDTO academicProgramGroup = (AcademicProgramGroupDTO)obj;
				List<DTOBase> academicProgramListByAcademicProgramGroup = AcademicProgramUtil.getAcademicProgramListByAcademicProgramGroupCode(academicProgramList, academicProgramGroup.getCode());
				if(academicProgramListByAcademicProgramGroup.size() >= 1) {
		%>
        <div class="col-lg-4">
			<br><br>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<input type="checkbox" id="chkAcademicProgram<%=academicProgramGroup.getCode()%>" onchange="toggleCheckListByPrefixId(this)"> <%=academicProgramGroup.getName()%> 
				</div>
				<div class="panel-body">
					<%=WebUtil.getTable("table", new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, "AcademicProgram" + academicProgramGroup.getCode(), academicProgramListByAcademicProgramGroup, fee.getAcademicProgramCodes().split("~"), StringUtil.getStrArr(academicProgramListByAcademicProgramGroup), "onchange=\"toggleCheckParent('chkAcademicProgram" + academicProgramGroup.getCode() + "', " + academicProgramListByAcademicProgramGroup.size() + ")\""), 2) %>
				</div>
			</div>
		</div>
		<%	
				}
			}
		%>
	</div>
</div>
<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-t-lg", "btn btn-primary", "align='center'") %>
