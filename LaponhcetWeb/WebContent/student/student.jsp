<%@ page import="java.util.*" %>
<%@ page import="com.mytechnopal.*" %>
<%@ page import="com.mytechnopal.base.*" %>
<%@ page import="com.mytechnopal.dto.*" %>
<%@ page import="com.mytechnopal.util.*" %>
<%@ page import="com.mytechnopal.webcontrol.*" %>
<%@ page import="com.laponhcet.dto.*" %>  
<%@ page import="com.laponhcet.util.*" %>

<%
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
	StudentDTO student = (StudentDTO) session.getAttribute(StudentDTO.SESSION_STUDENT);
	List<DTOBase> cityList = (ArrayList)session.getAttribute(CityDTO.SESSION_CITY_LIST);
	List<DTOBase> religionList = (ArrayList)session.getAttribute(ReligionDTO.SESSION_RELIGION_LIST);
	List<DTOBase> academicProgramList = (ArrayList)session.getAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
	List<DTOBase> schoolList = (ArrayList)session.getAttribute(SchoolDTO.SESSION_SCHOOL_LIST);
 %>
<div class="col-sm-12 m-b-lg">
	<div class="nav-tabs-custom">
	  	<ul class="nav nav-tabs">
		    <li class="active"><a href="#tabPersonalInfo" data-toggle="tab">Personal Information</a></li>
		    <li><a href="#tabScholastic" data-toggle="tab">Scholastic</a></li>
		    <li><a href="#tabParentInfo" data-toggle="tab" onclick="setEmergencyContact()">Parent/Guardian Information</a></li>
		    <li><a href="#tabContact" data-toggle="tab">Contact</a></li>
		</ul>
		<div class="tab-content">
		    <div class="col-sm-12 tab-pane active" id="tabPersonalInfo">  
		    	<div class="row"> 
		    		<div class="col-sm-3 text-center">
						<%=new FileInputWebControl().getFileInputWebControl(sessionInfo, "col-sm-4", false, "Photo", "ProfilePict", true, student.getProfilePict().getPict(), 1024000, FileUtil.IMG_FILENAME_EXT_LIST, 240, 240, null, "") %>	    		
		    		</div>
		    		<div class="col-sm-9">
			    		<div class="col-sm-4">
			    			<h5 class="m-t-lg">
			    				<strong>
			    		<%
			    		
			    			if(StringUtil.isEmpty(student.getCode())) {
			    		%>
				    			*ID is AUTO Generated
			    		<%
			    			}
			    			else {
			    		%>
			    				ID: <b><%=student.getCode()%></b>
			    		<%
			    			}
			    		%>
				    			</strong>
				    		</h5>
					    </div> 
				    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "RFID", "Scan RFID here", "Rfid", student.getRfid(), 16, WebControlBase.DATA_TYPE_STRING, "") %>
				    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Facebook ID", "Facebook ID", "FacebookId", student.getFacebookId(), 16, WebControlBase.DATA_TYPE_STRING, "") %>
			    		<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "LRN", "Learner Ref #", "LearnerReferenceNumber", student.getLearnerReferenceNumber(), 12, TextBoxWebControl.DATA_TYPE_STRING, "") %>
				    	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-8", true, "Academic Program", "AcademicProgram", academicProgramList, student.getAcademicProgram(), "--Select Program--", "0", "")%> 
						<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Cellphone No.", "Cellphone No.", "CpNumber", student.getCpNumber(), 11, TextBoxWebControl.DATA_TYPE_STRING, "")%>
						<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Landline No.", "Landline No.", "LandlineNumber", student.getLandlineNumber(), 11, TextBoxWebControl.DATA_TYPE_STRING, "")%>
						<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Email Address", "Email Address", "EmailAddress", student.getEmailAddress(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
				    </div>
					<div class="col-sm-12 no-padding">
						<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false,  "Salutation", "PrefixName", UserDTO.PREFIX_NAME_LIST, student.getPrefixName(), UserDTO.PREFIX_NAME_LIST, "", "", "")%>
						<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Last Name", "Last Name", "LastName", student.getLastName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
						<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3 no-padding", true, "First Name", "First Name", "FirstName", student.getFirstName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
						<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Middle Name", "Middle Name", "MiddleName", student.getMiddleName(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
						<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false, "Suffix", "SuffixName", UserDTO.SUFFIX_NAME_LIST, student.getSuffixName(), UserDTO.SUFFIX_NAME_LIST, "", "", "") %>
					</div>
					<div class="col-sm-12 m-t-lg no-padding">
						<div class="col-sm-6">
							<div class="panel panel-default">
						    	<div class="panel-heading">
						            Permanent Address
						        </div>
						        <div class="panel-body">
						           	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Street/Lot/Block", "Street", "StreetPermanent", student.getStreetPermanent(), 180, TextBoxWebControl.DATA_TYPE_STRING, "onchange='setPresentAddressToPermanentAddress()'")%>
									<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6 m-b", true, "Barangay", "Barangay", "BarangayPermanent", student.getBarangayPermanent(), 45, TextBoxWebControl.DATA_TYPE_STRING, "onchange='setPresentAddressToPermanentAddress()'")%>
									<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6", true, "City", "CityPermanent", cityList, student.getCityPermanent(), "", "", "onchange='setPresentAddressToPermanentAddress()'")%>
						        </div>
						    </div>
						</div>
						<div class="col-sm-6">
						    <div class="panel panel-default">
						        <div class="panel-heading">
						            Present Address
						            &nbsp;&nbsp;<input type="checkbox" id="chkSameAsPermanent" name="chkSameAsPermanent" onclick="setPresentAddressToPermanentAddressOnCheckboxClick()"> Same as Permanent
						        </div>
						        <div class="panel-body">
						           	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Street/Lot/Block", "Street", "StreetPresent", student.getStreetPresent(), 180, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContactAddress()'")%>
									<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6 m-b", true, "Barangay", "Barangay", "BarangayPresent", student.getBarangayPresent(), 45, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContactAddress()'")%>
									<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-6", true, "City", "CityPresent", cityList, student.getCityPresent(), "", "", "")%>

						        </div>
						    </div>
						</div>
					</div>
					<div class="col-sm-12 no-padding">
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-9", false, "Birth Place", "Birth Place", "BirthPlace", student.getBirthPlace(), 180, TextBoxWebControl.DATA_TYPE_STRING, "")%>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-3", true, "Birth Date", "Birth Date", "BirthDate", DateTimeUtil.getDateTimeToStr(student.getBirthDate(), "MM/dd/yyyy"), 10, TextBoxWebControl.DATA_TYPE_DATE, "", "maxDate: '-24M', changeMonth: true, changeYear: true")%>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", true, "Gender", "Gender", new String[] {UserDTO.GENDER_MALE, UserDTO.GENDER_FEMALE}, student.getGender(), new String[] {UserDTO.GENDER_MALE, UserDTO.GENDER_FEMALE}, "", "", "")%>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false, "Religion", "Religion", religionList, student.getReligion(), "", "", "")%>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-2", false, "Status", "MaritalStatus", new String[] {UserDTO.MARITAL_STATUS_SINGLE, UserDTO.MARITAL_STATUS_MARRIED, UserDTO.MARITAL_STATUS_SEPERATED, UserDTO.MARITAL_STATUS_WIDOW}, student.getMaritalStatus(), new String[] {UserDTO.MARITAL_STATUS_SINGLE, UserDTO.MARITAL_STATUS_MARRIED, UserDTO.MARITAL_STATUS_SEPERATED, UserDTO.MARITAL_STATUS_WIDOW}, "", "", "")%>
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Citizenship", "Citizenship", StringUtil.getCountryList(), student.getCitizenship(), StringUtil.getCountryList(), "", "", "")%>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Passport No.", "Passport No.", "PassportNumber", student.getPassportNumber(), 45, TextBoxWebControl.DATA_TYPE_STRING, "")%>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", false, "Skills", "Skills", "Skills", student.getSkills(), 180, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					</div>
				</div>
			</div>
			<div class="col-sm-12 no-padding  tab-pane" id="tabScholastic">
				<div class="row">
					<!-- Elementary School -->
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Elementary", "ElementarySchoolCompletedAt", schoolList, student.getElementarySchoolCompletedAt(), " Select School ", "0", "onblur='setSchoolLastAttendedInfo()'") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Year Graduated", "Year Graduated", "ElementarySchoolGraduatedYear", StringUtil.getFormattedNum(student.getElementarySchoolGraduatedYear(), StringUtil.NUMERIC_WHOLE_NUMBER_FORMAT_NO_COMMA) , 4, TextBoxWebControl.DATA_TYPE_INTEGER_EMPTY_WHEN_ZER0, "onblur='setSchoolLastAttendedInfo()'") %>
					<!-- Junior High School  -->
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Junior High School", "JuniorHighSchoolCompletedAt",schoolList,  student.getJuniorHighSchoolCompletedAt()," Select School ", "0", "onblur='setSchoolLastAttendedInfo()'") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Year Graduated", "Year Graduated", "JuniorHighSchoolGraduatedYear", StringUtil.getFormattedNum(student.getJuniorHighSchoolGraduatedYear(), StringUtil.NUMERIC_WHOLE_NUMBER_FORMAT_NO_COMMA) , 4, TextBoxWebControl.DATA_TYPE_INTEGER_EMPTY_WHEN_ZER0, "onblur='setSchoolLastAttendedInfo()'") %>
					<!-- Senior High School  -->
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Senior High School", "SeniorHighSchoolCompletedAt", schoolList, student.getSeniorHighSchoolCompletedAt(), " Select School ", "0", "onblur='setSchoolLastAttendedInfo()'") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Year Graduated", "Year Graduated", "SeniorHighSchoolGraduatedYear", StringUtil.getFormattedNum(student.getSeniorHighSchoolGraduatedYear(), StringUtil.NUMERIC_WHOLE_NUMBER_FORMAT_NO_COMMA) , 4, TextBoxWebControl.DATA_TYPE_INTEGER_EMPTY_WHEN_ZER0, "onblur='setSchoolLastAttendedInfo()'") %>
					<!-- Vocational School -->
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Vocational School", "VocationalSchoolCompletedAt", schoolList, student.getVocationalSchoolCompletedAt(), " Select School ", "0", "onblur='setSchoolLastAttendedInfo()'") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Year Graduated", "Year Graduated", "VocationalSchoolGraduatedYear", StringUtil.getFormattedNum(student.getVocationalSchoolGraduatedYear(), StringUtil.NUMERIC_WHOLE_NUMBER_FORMAT_NO_COMMA) , 4, TextBoxWebControl.DATA_TYPE_INTEGER_EMPTY_WHEN_ZER0, "onblur='setSchoolLastAttendedInfo()'") %>
					<!-- College School  -->
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "College","CollegeSchoolCompletedAt", schoolList, student.getCollegeSchoolCompletedAt(), " Select School ", "0", "onblur='setSchoolLastAttendedInfo()'") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Year Graduated", "Year Graduated", "CollegeSchoolGraduatedYear", StringUtil.getFormattedNum(student.getCollegeSchoolGraduatedYear(), StringUtil.NUMERIC_WHOLE_NUMBER_FORMAT_NO_COMMA) , 4, TextBoxWebControl.DATA_TYPE_INTEGER_EMPTY_WHEN_ZER0, "onblur='setSchoolLastAttendedInfo()'") %>
					<!-- Graduate School  -->
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Graduate School","GraduateSchoolCompletedAt", schoolList, student.getGraduateSchoolCompletedAt(), " Select School ", "0", "onblur='setSchoolLastAttendedInfo()'") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Year Graduated", "Year Graduated", "GraduateSchoolGraduatedYear", StringUtil.getFormattedNum(student.getGraduateSchoolGraduatedYear(), StringUtil.NUMERIC_WHOLE_NUMBER_FORMAT_NO_COMMA) , 4, TextBoxWebControl.DATA_TYPE_INTEGER_EMPTY_WHEN_ZER0, "onblur='setSchoolLastAttendedInfo()'") %>
					<!-- Last School Attended -->
					<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-10", false, "Last Attended", "SchoolLastAttendedAt", schoolList, student.getSchoolLastAttendedAt(), " Select School ", "0", "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-2", false, "Year", "Year", "SchoolLastAttendedYear", StringUtil.getFormattedNum(student.getSchoolLastAttendedYear(), StringUtil.NUMERIC_WHOLE_NUMBER_FORMAT_NO_COMMA) , 4, TextBoxWebControl.DATA_TYPE_INTEGER_EMPTY_WHEN_ZER0, "") %>

					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-7", false, "Highest Educational Attainment", "Highest Educational Attainment", "HighestEducationalAttainment", student.getHighestEducationalAttainment(), 90, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-5", false, "Major", "Major", "HighestEducationalAttainmentMajor", student.getHighestEducationalAttainmentMajor(), 60, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", false, "Entrance Credentials", "Entrance Credentials", "EntranceCredentials", student.getEntranceCredentials(), 90, TextBoxWebControl.DATA_TYPE_STRING, "") %>
		    	</div>
		    </div>
		    <div class="col-sm-12 no-padding tab-pane" id="tabParentInfo">
		   		<div class="row">
			    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Father's Name", "Father's Name", "FatherName", student.getFatherName(), 60, TextBoxWebControl.DATA_TYPE_STRING, "") %>
			    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Occupation", "Occupation", "FatherOccupation", student.getFatherOccupation(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
			    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Cellphone No.", "Cellphone No.", "FatherCpNumber", student.getFatherCpNumber(), 11, TextBoxWebControl.DATA_TYPE_STRING, "") %>  	
			    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Mother's Name", "Mother's Name", "MotherName", student.getMotherName(), 60, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContact()'") %>
			    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Occupation", "Occupation", "MotherOccupation", student.getMotherOccupation(), 45, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContact()'") %>
			    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Cellphone No.", "Cellphone No.", "MotherCpNumber", student.getMotherCpNumber(), 11, TextBoxWebControl.DATA_TYPE_STRING, "onblur='setEmergencyContact()'") %>
			    	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", false, "Guardian's Name", "Guardian's Name", "GuardianName", student.getGuardianName(), 90, TextBoxWebControl.DATA_TYPE_STRING, "") %>
			    	<%=new ComboBoxWebControl().getComboBoxWebControl(sessionInfo, "col-sm-4", false, "Guardian's Relation", "GuardianRelation", new String[] {"PARENT", "RELATIVE", "NON-RELATIVE"}, student.getGuardianRelation(), new String[] {"PARENT", "RELATIVE", "NON-RELATIVE"}, "", "", "") %>
		    	</div>
		    </div>
		    <div class="col-sm-12 no-padding tab-pane" id="tabContact">
		    	<div class="row">
			       	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-8", true, "Contact Person", "Emergency Contact Person", "ContactPerson", student.getContactPerson(), 60, TextBoxWebControl.DATA_TYPE_STRING, "") %>
			       	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-4", true, "Relation", "Relation", "ContactRelation", student.getContactRelation(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
			       	<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-12", true, "Address", "Address", "ContactAddress", student.getContactAddress(), 135, TextBoxWebControl.DATA_TYPE_STRING, "") %>
			 		<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", false, "Cellphone No.", "Cellphone No.", "ContactCPNumber", student.getContactCPNumber(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", false, "Landline No.", "Landline No.", "ContactLandlineNumber", student.getContactLandlineNumber(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
			 		<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", false, "Email Address", "Email Address", "ContactEmailAddress", student.getContactEmailAddress(), 45, TextBoxWebControl.DATA_TYPE_STRING, "") %>
					<%=new TextBoxWebControl().getTextBoxWebControl(sessionInfo, "col-sm-6", false, "Facebook ID", "Facebook ID", "ContactFacebookId", student.getContactFacebookId(), 16, TextBoxWebControl.DATA_TYPE_STRING, "") %>

				</div>
		    </div> 
	    </div>
	</div>
	<div class="col-sm-12">
	<%=new TransitionButtonWebControl().getTransitionButtonWebControl(sessionInfo, "col-sm-12", "btn btn-primary", "align='center'") %>
	</div>	  
</div>

<%
if(sessionInfo.isCurrentLinkDataEntry()) {
	if(!StringUtil.isEmpty(student.getStreetPermanent())) {
		if(student.getStreetPermanent().equalsIgnoreCase(student.getStreetPresent()) && student.getBarangayPermanent().equalsIgnoreCase(student.getBarangayPresent()) && student.getCityPermanent().getCode().equalsIgnoreCase(student.getCityPresent().getCode())) {
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
	
	function setSchoolLastAttendedInfo() {
		// Graduate School
		if(document.getElementById("cboGraduateSchoolCompletedAt").value > 0) {
			document.getElementById("cboSchoolLastAttendedAt").value = document.getElementById("cboGraduateSchoolCompletedAt").value;
			document.getElementById("txtSchoolLastAttendedYear").value = document.getElementById("txtGraduateSchoolGraduatedYear").value;
		}
		else {
			document.getElementById("txtGraduateSchoolGraduatedYear").value = "";
			// College School
			if(document.getElementById("cboCollegeSchoolCompletedAt").value > 0) {
				document.getElementById("cboSchoolLastAttendedAt").value = document.getElementById("cboCollegeSchoolCompletedAt").value;
				document.getElementById("txtSchoolLastAttendedYear").value = document.getElementById("txtCollegeSchoolGraduatedYear").value;
			}
			else {
				document.getElementById("txtCollegeSchoolGraduatedYear").value = "";
		 		// Vocational School
		 		if(document.getElementById("cboVocationalSchoolCompletedAt").value > 0) {
		 			document.getElementById("cboSchoolLastAttendedAt").value = document.getElementById("cboVocationalSchoolCompletedAt").value;
		 			document.getElementById("txtSchoolLastAttendedYear").value = document.getElementById("txtVocationalSchoolGraduatedYear").value;
		 		}
		 		else {
		 			document.getElementById("txtVocationalSchoolGraduatedYear").value = "";
		 	 		// Senior High School
		 	 		if(document.getElementById("cboSeniorHighSchoolCompletedAt").value > 0) {
		 	 			document.getElementById("cboSchoolLastAttendedAt").value = document.getElementById("cboSeniorHighSchoolCompletedAt").value;
		 	 			document.getElementById("txtSchoolLastAttendedYear").value = document.getElementById("txtSeniorHighSchoolGraduatedYear").value;
		 	 		}
		 	 		else {
		 	 			document.getElementById("txtSeniorHighSchoolGraduatedYear").value = "";
		 	 	 		// Junior High School
		 	 	 		if(document.getElementById("cboJuniorHighSchoolCompletedAt").value > 0) {
		 	 	 			document.getElementById("cboSchoolLastAttendedAt").value = document.getElementById("cboJuniorHighSchoolCompletedAt").value;
		 	 	 			document.getElementById("txtSchoolLastAttendedYear").value = document.getElementById("txtJuniorHighSchoolGraduatedYear").value;
		 	 	 		}
		 	 	 		else {
		 	 	 			document.getElementById("txtJuniorHighSchoolGraduatedYear").value = "";
		 	 	 	 		// Elementary School
		 	 	 	 		if(document.getElementById("cboElementarySchoolCompletedAt").value > 0) {
		 	 	 	 			document.getElementById("cboSchoolLastAttendedAt").value = document.getElementById("cboElementarySchoolCompletedAt").value;
		 	 	 	 			document.getElementById("txtSchoolLastAttendedYear").value = document.getElementById("txtElementarySchoolGraduatedYear").value;
		 	 	 	 		}
		 	 	 	 		else {
		 	 	 	 			document.getElementById("txtElementarySchoolGraduatedYear").value = ""
		 	 	 	 		}
		 	 	 		}
		 	 		}
		 		}
			}
		}
	}
	
	function setEmergencyContact() {
		if(document.getElementById("txtMotherName").value != null){
			document.getElementById("txtGuardianName").value = document.getElementById("txtMotherName").value;
			document.getElementById("cboGuardianRelation").value = "PARENT";
			document.getElementById("txtContactPerson").value = document.getElementById("txtMotherName").value;
			document.getElementById("txtContactRelation").value = "MOTHER";
			document.getElementById("txtContactCPNumber").value = document.getElementById("txtMotherCpNumber").value;
		}
		else if(document.getElementById("txtFatherName").value != null){
			document.getElementById("txtGuardianName").value = document.getElementById("txtFatherName").value;
			document.getElementById("cboGuardianRelation").value = "PARENT";
			document.getElementById("txtContactPerson").value = document.getElementById("txtFatherName").value;
			document.getElementById("txtContactRelation").value = "FATHER";
			document.getElementById("txtContactCPNumber").value = document.getElementById("txtFatherCpNumber").value;
		}
		document.getElementById("txtContactAddress").value = document.getElementById("txtStreetPermanent").value + ", Barangay " + document.getElementById("txtBarangayPermanent").value + " " + document.getElementById("cboCityPermanent").options[document.getElementById("cboCityPermanent").selectedIndex].text.trim();
	}
	
	<%
	if(sessionInfo.isCurrentLinkAdd()) {
	%>
		document.getElementById("cboPrefixName").focus();
	<%
	}
	%>
</script>		