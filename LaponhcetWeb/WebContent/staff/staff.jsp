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
	StaffDTO staff = (StaffDTO)session.getAttribute(StaffDTO.SESSION_STAFF);
	List<DTOBase> cityList = (ArrayList)session.getAttribute(CityDTO.SESSION_CITY_LIST);
	List<DTOBase> religionList = (ArrayList)session.getAttribute(ReligionDTO.SESSION_RELIGION_LIST);
	List<DTOBase> academicProgramList = (ArrayList<DTOBase>)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
%>
<input type="hidden" name="txtCheckedAcademicProgramCodes" id="txtCheckedAcademicProgramCodes" value="<%=staff.getAcademicProgramCodes()%>"/>
<div class="col-sm-12">
	<div class="col-sm-3 no-padding">
		<%=new FileInputWebControl().getFileInputWebControl(sessionInfo, "col-sm-12", false, "Profile Picture", "ProfilePict", false, staff.getProfilePict(), 1024000, FileUtil.IMG_FILENAME_EXT_LIST, 240, 280, null, "") %>
	</div>
	<div class="col-sm-9">
		<div class="col-sm-3 no-padding">
   			<h5 class="m-t-lg"><strong>
   		<%
   			if(StringUtil.isEmpty(staff.getCode())) {
   		%>
    			*ID is AUTO Generated
   		<%
   			}
   			else {
   		%>
   				ID: <b><%=staff.getCode()%></b>
   		<%
   			}
   		%>
    		</strong></h5>
    	</div> 
		<%= new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Rfid", "Rfid", "Rfid", staff.getRfid(), 20, TextBoxWebControl.DATA_TYPE_STRING, "") %>
		<%= new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-5 no-padding", false, "Cellphone No.", "Cellphone No.", "CpNumber", staff.getCpNumber(), 11, TextBoxWebControl.DATA_TYPE_STRING, "")%>
		<div class="col-sm-3 no-padding"></div>
		<%= new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Program Graduated", "Program Graduated", "ProgramGraduated", staff.getProgramGraduated(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
		<%= new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-5 no-padding", true, "Email Address", "Email Address", "EmailAddress", staff.getEmailAddress(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
		<%= StaffUtil.getTableWithLabel(sessionInfo, "Academic Program", "AcademicProgram", true, academicProgramList, staff.getAcademicProgramCodes(), "", 8, "border-left-right border-top-bottom p-xs b-r-md") %>
	</div>
</div>
<div class="col-sm-12 no-padding">
	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false,  "Salutation", "PrefixName", UserDTO.PREFIX_NAME_LIST, staff.getPrefixName(), UserDTO.PREFIX_NAME_LIST, "", "", "")%>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Last Name", "Last Name", "LastName", staff.getLastName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3 no-padding", true, "First Name", "First Name", "FirstName", staff.getFirstName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", true, "Middle Name", "Middle Name", "MiddleName", staff.getMiddleName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false, "Suffix", "SuffixName", UserDTO.SUFFIX_NAME_LIST, staff.getSuffixName(), UserDTO.SUFFIX_NAME_LIST, "", "", "") %>
</div>
<div class="col-lg-6 m-t-lg">
	<div class="panel panel-default">
    	<div class="panel-heading">
            Permanent Address
        </div>
        <div class="panel-body">
			<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Street", "Street", "StreetPermanent", staff.getStreetPermanent(), 180, TextBoxWebControl.DATA_TYPE_STRING, "onchange='setPresentAddressToPermanentAddress()'") %>
			<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Barangay", "Barangay", "BarangayPermanent", staff.getBarangayPermanent(), 45, TextBoxWebControl.DATA_TYPE_STRING, "onchange='setPresentAddressToPermanentAddress()'") %>
        	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6 m-b-lg", true, "City", "CityPermanent", cityList, staff.getCityPermanent(), "", "", "onchange='setPresentAddressToPermanentAddress()'")%>
        </div>
    </div>
</div>
<div class="col-lg-6 m-t-lg">
    <div class="panel panel-default">
        <div class="panel-heading">
        	<input type="hidden" id="txtdisableOrNot" name="txtdisableOrNot" value=""/>
            Present Address
            <%
            if(sessionInfo.isCurrentLinkDataEntry()) {
            %>
            &nbsp;&nbsp;<input type="checkbox" id="chkSameAsPermanent" name="chkSameAsPermanent" onclick="combinationOfTwoFunctions()"> Same as Permanent
			<%
            }
			%>
        </div>
        <div class="panel-body">
           	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Street", "Street", "StreetPresent", staff.getStreetPresent(), 180, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContactAddress()'") %>
			<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6 ", true, "Barangay", "Barangay", "BarangayPresent", staff.getBarangayPresent(), 45, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContactAddress()'") %>
			<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6 m-b-lg", true, "City", "CityPresent", cityList, staff.getCityPresent(), "", "", "")%>
        </div>
    </div>
</div>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Birth Date", "Birth Date", "BirthDate", DateTimeUtil.getDateTimeToStr(staff.getBirthDate(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true") %>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", true, "Gender", "Gender", new String[] {UserDTO.GENDER_MALE, UserDTO.GENDER_FEMALE}, staff.getGender(), new String[] {UserDTO.GENDER_MALE, UserDTO.GENDER_FEMALE}, "", "", "") %>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", true, "Religion", "Religion", religionList, staff.getReligion(), "", "", "")%>
<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-3", true, "Status", "MaritalStatus", new String[] {UserDTO.MARITAL_STATUS_SINGLE, UserDTO.MARITAL_STATUS_MARRIED, UserDTO.MARITAL_STATUS_SEPERATED, UserDTO.MARITAL_STATUS_WIDOW}, staff.getMaritalStatus(), new String[] {UserDTO.MARITAL_STATUS_SINGLE, UserDTO.MARITAL_STATUS_MARRIED, UserDTO.MARITAL_STATUS_SEPERATED, UserDTO.MARITAL_STATUS_WIDOW}, "", "", "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Job Role", "Job Role", "JobRole", staff.getJobRole(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", true, "Assigned Office", "Assigned Office", "AssignedOffice", staff.getAssignedOffice(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>	
<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12 m-b-lg m-t-lg", "btn btn-primary", "align='center'") %>

<%
if(sessionInfo.isCurrentLinkDataEntry()) {
	if(!StringUtil.isEmpty(staff.getStreetPermanent())) {
		if(staff.getStreetPermanent().equalsIgnoreCase(staff.getStreetPresent()) && staff.getBarangayPermanent().equalsIgnoreCase(staff.getBarangayPresent()) && staff.getCityPermanent().getCode().equalsIgnoreCase(staff.getCityPresent().getCode())) {
%>
	<script>
		document.getElementById("chkSameAsPermanent").checked = "checked";
		document.getElementById("txtStreetPresent").disabled = true;
		document.getElementById("txtBarangayPresent").disabled = true;
		document.getElementById("cboCityPresent").disabled = true;
	</script>
<%			
		}
		else {
%>
	<script>
		document.getElementById("chkSameAsPermanent").checked = "";
		document.getElementById("txtStreetPresent").disabled = false;
		document.getElementById("txtBarangayPresent").disabled = false;
		document.getElementById("cboCityPresent").disabled = false;
	</script>
<%			
		}
	}
}
%>

<script>
	function setPresentAddressToPermanentAddressOnCheckboxClick() {
		setPresentAddressToPermanentAddress();
		toggleEnable("txtStreetPresent");
		toggleEnable("txtBarangayPresent");
		toggleEnable("cboCityPresent");
	}
	
	function setPresentAddressToPermanentAddress() {
		if(document.getElementById("chkSameAsPermanent").checked) {
			document.getElementById("txtStreetPresent").value = document.getElementById("txtStreetPermanent").value;
			document.getElementById("txtBarangayPresent").value = document.getElementById("txtBarangayPermanent").value;
			document.getElementById("cboCityPresent").value = document.getElementById("cboCityPermanent").value;
		}
	}
	<%
	if(sessionInfo.isCurrentLinkAdd()) {
	%>
		document.getElementById("cboLevel").focus();
	<%
	}
	%>
</script>

<script>
	disableOrNot();

	function disableOrNot() {
		if(document.getElementById("chkSameAsPermanent").checked) {
			document.getElementById("txtdisableOrNot").value = "1";
		}else {
			document.getElementById("txtdisableOrNot").value = "";
		}
	}

	function combinationOfTwoFunctions() {
		setPresentAddressToPermanentAddressOnCheckboxClick()
		disableOrNot();
	}
</script>

<script>
	$(function(){
		$('input:checkbox').click(function(){
			getProgramGroupChecked();
		});
	});
</script>

<script>
	function getProgramGroupChecked(){
		var str = [];
		$.each($("input:checkbox[name='chkProgramCodes']:checked"), function(){            
			str.push($(this).val());
		});
		document.getElementById("txtCheckedAcademicProgramCodes").value = str.join("~");
	}
</script>