<%@ page import="com.mytechnopal.base.WebControlBase"%>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>
<%@ page import="com.laponhcet.action.register.*" %>
<%@ page import="java.util.*" %>


<%
 	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	ActionResponse actionResponse = (ActionResponse) session.getAttribute(ActionResponse.SESSION_ACTION_RESPONSE);
 	RegisterDTO register = (RegisterDTO) session.getAttribute(RegisterDTO.SESSION_REGISTER);
%>

 <div class="ibox float-e-margins">
     <div class="ibox-content">
      	<div class="row">
      		<div class="col-sm-3">
      			<%=new FileInputWebControl().getFileInputWebControl(sessionInfo, "col-sm-12", false, "Photo", "ProfilePict", true, register.getProfilePict().getPict(), 1024000, FileUtil.IMG_FILENAME_EXT_LIST, 240, 240, null, "") %>	    		
			</div>
			<div class="col-sm-9">
			    <%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "RFID", "Scan RFID here", "Rfid", register.getRfid(), 10, WebControlBase.DATA_TYPE_STRING, "") %>		
				<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6", true, "Gender", "Gender", new String[] {UserDTO.GENDER_MALE, UserDTO.GENDER_FEMALE}, register.getGender(), new String[] {UserDTO.GENDER_MALE, UserDTO.GENDER_FEMALE}, "", "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Birthday", "Birthday", "BirthDate", DateTimeUtil.getDateTimeToStr(register.getBirthDate(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "") %>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", false, "Cellphone No.", "Cellphone No.", "CpNumber", register.getCpNumber(), 11, TextBoxWebControl.DATA_TYPE_STRING, "")%>
				<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", false, "Email Address", "Email Address", "EmailAddress", register.getEmailAddress(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>		
			</div>
			
			<div class="col-sm-12">
				<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false, "Prefix", "PrefixName", UserDTO.PREFIX_NAME_LIST, register.getPrefixName(), UserDTO.PREFIX_NAME_LIST, "", "", "") %>
		      	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Last Name", "Last Name", "LastName", register.getLastName(), 45, StringUtil.NORMAL_TEXT, "") %>
		      	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "First Name", "First Name", "FirstName", register.getFirstName(), 45, StringUtil.NORMAL_TEXT, "") %>
		      	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", false, "Middle Name", "Middle Name", "MiddleName", register.getMiddleName(), 45, StringUtil.NORMAL_TEXT, "") %>
		      	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-1", false, "Suffix", "SuffixName", UserDTO.SUFFIX_NAME_LIST, register.getSuffixName(), UserDTO.SUFFIX_NAME_LIST, "", "", "") %>
		      	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Institution Connected With", "Institution...", "InstitutionConnectedWith", register.getInstitutionConnectedWith(), 90, StringUtil.NORMAL_TEXT, "") %>
		      	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Occupation", "Occupation", "Occupation", register.getOccupation(), 45, StringUtil.NORMAL_TEXT, "") %>	      	
			</div>
			<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-primary", "align='center'") %>
		</div>
     </div>
 </div>