package com.laponhcet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.AcademicProgramSubgroupDAO;
import com.laponhcet.dao.CourseDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicProgramSubgroupDTO;
import com.laponhcet.dto.CourseDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageIndividualDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.SessionInfo;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;
import com.mytechnopal.util.WebUtil;
import com.mytechnopal.webcontrol.CheckBoxWebControl;

public class MessageUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static void getMessageListByMessageType(String messageType){
		
	}
	
	public static void setPaginationRecord(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> messageIndividualList, List<DTOBase> messageAcademicProgramGroupList, List<DTOBase> messageAcademicProgramSubgroupList,List<DTOBase> messageCourseList) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			MessageDTO message = (MessageDTO) dto;
			message.setPaginationRecord(new String[] {message.getCode(), MessageUtil.getMessageTypeCodesHTML(message, messageIndividualList, messageAcademicProgramGroupList, messageAcademicProgramSubgroupList, messageCourseList), message.getContent()});
		}
	}
	
	public static String getMessageTypeCodesHTML(MessageDTO message, List<DTOBase> messageIndividualList, List<DTOBase> messageAcademicProgramGroupList, List<DTOBase> messageAcademicProgramSubgroupList,List<DTOBase> messageCourseList){
		StringBuffer str = new StringBuffer();
		String[] messageTypeCodesArr = new String[]{"","", message.getMessageTypeCodes()};
		// INDIVIDUAL
		if(isExistingInMessageIndividual(messageIndividualList, message.getCode())){
			messageTypeCodesArr[0] = MessageDTO.SCHOOL_MESSAGE_TYPE_LIST[0];
			message.setRecipientList(getRecipientIndividualList(messageIndividualList, message.getCode()));
		// ACADEMIC PROGRAM GROUP
		}else if(isExistingInMessageAcademicProgramGroup(messageAcademicProgramGroupList, message.getCode())){
			messageTypeCodesArr[0] = MessageDTO.SCHOOL_MESSAGE_TYPE_LIST[1];
			MessageAcademicProgramGroupDTO messageAcademicProgramGroupDTO = new MessageAcademicProgramGroupDAO().getMessageAcademicProgramGroupByMessageCode(message.getCode());
			messageTypeCodesArr[1] = getAcademicProgramGroupCodesArray(messageAcademicProgramGroupDTO.getAcademicProgramGroupCodes().split("~"));
		// ACADEMIC PROGRAM SUBGROUP
		}else if(isExistingInMessageAcademicProgramSubgroup(messageAcademicProgramSubgroupList, message.getCode())){
			messageTypeCodesArr[0] = MessageDTO.SCHOOL_MESSAGE_TYPE_LIST[2];
			MessageAcademicProgramSubgroupDTO messageAcademicProgramSubgroupDTO = new MessageAcademicProgramSubgroupDAO().getMessageAcademicProgramSubgroupByMessageCode(message.getCode());
			messageTypeCodesArr[1] = getAcademicProgramSubgroupCodesArray(messageAcademicProgramSubgroupDTO.getAcademicProgramSubgroupCodes().split("~"));
		// COURSE
		}else if(isExistingInMessageCourse(messageCourseList, message.getCode())){
			messageTypeCodesArr[0] = MessageDTO.SCHOOL_MESSAGE_TYPE_LIST[3];
			MessageCourseDTO messageCourse = new MessageCourseDAO().getMessageCourseByMessageCode(message.getCode());
			messageTypeCodesArr[1] = getCourseCodesArray(messageCourse.getCourseCode().split("~"));
		}
		str.append(getLabeled(messageTypeCodesArr[0], "label-primary"));
		if(StringUtil.isEmpty(messageTypeCodesArr[1])){
			str.append(getRecipientListModalTableWithButton(message.getRecipientList(), message, "Recipient(s)", "label-default"));
		}else{
			str.append(getLabeledList(messageTypeCodesArr[1], "label-default"));
		}
		str.append(getLabeled("MEDIUM", "label-primary"));
		str.append(getLabeledList(getStrArrayValueArray(MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST, MessageDTO.SCHOOL_MESSAGE_MEDIUM_LIST, messageTypeCodesArr[2], true), "label-default"));
		return str.toString();
	}
	
	public static List<DTOBase> getRecipientIndividualList(List<DTOBase> individualList, String messageCode){
		List<DTOBase> newList = new ArrayList<DTOBase>();
		for(DTOBase obj: individualList){
			MessageIndividualDTO messageIndividual = (MessageIndividualDTO) obj;
			if(messageCode.equalsIgnoreCase(messageIndividual.getMessageCode())){
				UserDTO user = new UserDAO().getUserByCode(messageIndividual.getUser().getCode());
				newList.add(user);
			}
		}
		return newList;
	}
	
	public static List<DTOBase> getMessageByMessageCode(List<DTOBase> messageList, String messageIndividualMessageCode){
		List<DTOBase> newList = new ArrayList<DTOBase>();
		for(DTOBase obj: messageList){
			MessageDTO message = (MessageDTO) obj;
			if(messageIndividualMessageCode.equalsIgnoreCase(message.getCode())){
				newList.add(message);
			}
		}
		return newList;
	}
	
	public static String getLabeledList(String CodesStr, String labelClass){
		String labeledList = "";
		String[] strArray = CodesStr.split("~");
		for(int x=0; x< strArray.length; x++){
			labeledList += "<div class='col-sm-12 no-padding'><span class=\"label "+ labelClass +"\">"+ strArray[x] + "</span></div>";
		}
		return labeledList;
	}
	
	public static String getLabeledList(String[] CodesStr, String labelClass){
		String labeledList = "";
		for(int x=0; x< CodesStr.length; x++){
			labeledList += "<div class='col-sm-12 no-padding'><span class=\"label "+ labelClass +"\">"+ CodesStr[x] + "</span></div>";
		}
		return labeledList;
	}
	
	public static String getLabeled(String CodesStr, String labelClass){
		return "<div class='col-sm-12 no-padding'><span class=\"label "+ labelClass +"\">" + CodesStr + "</span></div>";
	}
	
	public static String getRecipientListModalTableWithButton(List<DTOBase> userList, MessageDTO message, String CodesStr, String labelClass){
		StringBuffer str = new StringBuffer();
		str.append("<div class='col-sm-12 no-padding'>");
		str.append("<span class=\"label "+ labelClass +"\">" + userList.size() + " "+ CodesStr + "</span>");
		str.append("<a href=\"#\" data-toggle=\"modal\" data-target=\"#modal-form-" + message.getCode() + "\">");
		str.append("&nbsp;<i class=\"fa fa-tags\"></i></a></div>");
		str.append("<div id=\"modal-form-" + message.getCode() + "\" class=\"modal fade\" aria-hidden=\"true\">");
		str.append("	<div class=\"modal-dialog\">");
		str.append("        <div class=\"modal-content\">");
		str.append("    		<div class=\"modal-body\">");
		str.append("    			<div class=\"row\" style=\"padding-top:20px;\">");
		str.append("      			<h3 class=\"m-l\">Recipient(s)</h3>");
		str.append(						getLabeledList(userList));
		str.append("				</div>");
		str.append("			</div>");
		str.append("		</div>");
		str.append("	</div>");
		str.append("</div>");
		return str.toString();
	}
	
	public static String getLabeledList(List<DTOBase> userList){
		StringBuffer str = new StringBuffer();
		str.append("<div class=\"col-sm-12\"><ul class=\"list-group clear-list m-t\">");
		for(int x =0; x<userList.size(); x++){
			UserDTO user = (UserDTO) userList.get(x);
			str.append("    <li class=\"list-group-item\">");
			str.append("        <span class=\"pull-right\">" + user.getCpNumber() + "</span>");
			str.append("        <span class=\"label label-primary\">" + (x + 1) + "</span>" + user.getName(true, false, false));
			str.append("    </li>");
		}
		str.append("</ul></div>");
		return str.toString();
	}
	
	public static boolean isExistingInMessageIndividual(List<DTOBase> messageIndividualList, String messageCode){
		boolean isExisting = false;
		for(DTOBase obj: messageIndividualList){
			MessageIndividualDTO messageIndividual = (MessageIndividualDTO) obj;
			if(messageCode.equalsIgnoreCase(messageIndividual.getMessageCode())){
				isExisting = true;
			}
		}
		return isExisting;
	}
	
	public static boolean isExistingInMessageAcademicProgramGroup(List<DTOBase> messageAcademicProgramGroupList, String messageCode){
		boolean isExisting = false;
		for(DTOBase obj: messageAcademicProgramGroupList){
			MessageAcademicProgramGroupDTO messageAcademicProgramGroup = (MessageAcademicProgramGroupDTO) obj;
			if(messageCode.equalsIgnoreCase(messageAcademicProgramGroup.getMessage().getCode())){
				isExisting = true;
			}
		}
		return isExisting;
	}
	
	public static boolean isExistingInMessageAcademicProgramSubgroup(List<DTOBase> messageAcademicProgramSubgroupList, String messageCode){
		boolean isExisting = false;
		for(DTOBase obj: messageAcademicProgramSubgroupList){
			MessageAcademicProgramSubgroupDTO messageAcademicProgramSubgroup = (MessageAcademicProgramSubgroupDTO) obj;
			if(messageCode.equalsIgnoreCase(messageAcademicProgramSubgroup.getMessage().getCode())){
				isExisting = true;
			}
		}
		return isExisting;
	}
	
	public static boolean isExistingInMessageCourse(List<DTOBase> messageCourseList, String messageCode){
		boolean isExisting = false;
		for(DTOBase obj: messageCourseList){
			MessageCourseDTO messagecourse = (MessageCourseDTO) obj;
			if(messageCode.equalsIgnoreCase(messagecourse.getMessage().getCode())){
				isExisting = true;
			}
		}
		return isExisting;
	}
	
	public static String getAcademicProgramGroupCodesArray(String[] academicProgramGroupCodes){
		for(int x=0; x< academicProgramGroupCodes.length; x++){
			AcademicProgramGroupDTO academicProgramGroup = (AcademicProgramGroupDTO) DTOUtil.getObjByCode(new AcademicProgramGroupDAO().getAcademicProgramGroupList(), academicProgramGroupCodes[x]);
			academicProgramGroupCodes[x] = academicProgramGroup.getDisplayText();
		}
		return String.join("~", academicProgramGroupCodes);
	}
	
	public static String getAcademicProgramSubgroupCodesArray(String[] academicProgramSubgroupCodes){
		for(int x=0; x< academicProgramSubgroupCodes.length; x++){
			AcademicProgramSubgroupDTO academicProgramSubgroup = (AcademicProgramSubgroupDTO) DTOUtil.getObjByCode(new AcademicProgramSubgroupDAO().getAcademicProgramSubgroupList(), academicProgramSubgroupCodes[x]);
			academicProgramSubgroupCodes[x] = academicProgramSubgroup.getDisplayText();
		}
		return String.join("~", academicProgramSubgroupCodes);
	}
	
	public static String getCourseCodesArray( String[] courseCodes){
		for(int x=0; x< courseCodes.length; x++){
			CourseDTO course = (CourseDTO) DTOUtil.getObjByCode(new CourseDAO().getCourseList(), courseCodes[x]);
			courseCodes[x] = course.getDisplayText();
		}
		return String.join("~", courseCodes);
	}
	
	public static String getStrArrayValue(String[] codeList, String[] displayList, String selectedValue, boolean getDisplayText){
		String valueStr = "";
		if(getDisplayText){
			int codeIndex = StringUtil.getIndexFromArrayStr(codeList, selectedValue);
			valueStr = codeIndex>=0?displayList[codeIndex]:"";
		}else{
			int codeIndex = StringUtil.getIndexFromArrayStr(displayList, selectedValue);
			valueStr = codeIndex>=0?codeList[codeIndex]:"";
		}
		return valueStr;
	}
	
	public static String[] getStrArrayValueArray(String[] codeList, String[] displayList, String selectedValueArray, boolean getDisplayText){
		String[] strArray = selectedValueArray.split("~");
		String codeTextArray = "";
		for(int x = 0; x < strArray.length; x++){
			codeTextArray += "~" + getStrArrayValue(codeList, displayList, strArray[x], getDisplayText);
		}
		return codeTextArray.split("~");
	}
	
	public static String getPaneledCheckbox(SessionInfo sessionInfo, String gridClass, String panelName, String panelClass, Boolean isHeaderCheckbox, String panelHeaderLabel, List<DTOBase> ObjList, String strArrCodes,  int tableColCount, String webSettingForPanelBody){
		StringBuffer str = new StringBuffer();
		str.append("<div id=\"div" + panelName + "\">");
		str.append("<div class=\"" + gridClass  + "\">");
		str.append("<div class=\"" + panelClass + "\">");
		str.append("<div class=\"panel-heading\">");
		if(isHeaderCheckbox && sessionInfo.isCurrentLinkDataEntry()){
			str.append("<input type=\"checkbox\" id=\"chk" + panelName + "\" onchange=\"toggleCheckListByPrefixId(this)\">");
		}
		str.append("&nbsp;&nbsp;" + panelHeaderLabel + " </div>");
	    str.append("<div class=\"panel-body\" " + webSettingForPanelBody  + " >");
		str.append(WebUtil.getTable(new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, panelName, ObjList, strArrCodes.split("~"), StringUtil.getStrArr(ObjList), "onchange=\"toggleCheckParent('chk" + panelName + "', " + ObjList.size() + ")\""), tableColCount));
	    str.append("</div></div></div></div>");  
	    return str.toString();
	}
	
	public static String getPaneledCheckbox(SessionInfo sessionInfo, String gridClass, String panelName, String panelClass, Boolean isHeaderCheckbox, String panelHeaderLabel, List<DTOBase> ObjList, String selectedValue , String strArrCodes,  int tableColCount, String webSettingForPanelBody){
		StringBuffer str = new StringBuffer();
		str.append("<div id=\"div" + panelName + "\">");
		str.append("<div class=\"" + gridClass  + "\">");
		str.append("<div class=\"" + panelClass + "\">");
		str.append("<div class=\"panel-heading\">");
		if(isHeaderCheckbox && sessionInfo.isCurrentLinkDataEntry()){
			str.append("<input type=\"checkbox\" id=\"chk" + panelName + "\" onchange=\"toggleCheckListByPrefixId(this)\">");
		}
		str.append("&nbsp;&nbsp;" + panelHeaderLabel + " </div>");
	    str.append("<div class=\"panel-body\" " + webSettingForPanelBody  + " >");
		str.append(WebUtil.getTable(new CheckBoxWebControl().getCheckBoxWebControlArr(sessionInfo, panelName + selectedValue, ObjList, strArrCodes.split("~"), StringUtil.getStrArr(ObjList), "onchange=\"toggleCheckParent('chk" + panelName + selectedValue + "', " + ObjList.size() + ")\""), tableColCount));
	    str.append("</div></div></div></div>");  
	    return str.toString();
	}
	
	
	public static List<DTOBase> getStrToObjectList(String[] displayList, String[] valueList){
		List<DTOBase> list = new ArrayList<DTOBase>();
		for(int i=0; i<displayList.length; i++){
			DTOBase dto = new DTOBase();
			dto.setId(i);
			dto.setCode(valueList[i]);
			dto.setDisplayText(displayList[i]);
			list.add(dto);
		}
		return list;
	}
	
	public static String getPanelCheckboxJavaScriptForComboBoxSelection(SessionInfo sessionInfo, List<DTOBase> messageTypeList, String selectedCodes, MessageDTO message, String messageRecipientGroup){
		StringBuffer str = new StringBuffer();
		StringBuffer strHideToggle = new StringBuffer();
		if(sessionInfo.isCurrentLinkDataEntry()){
		str.append("<script>\n");
		str.append("hideMessageTypeCheckboxPanel();\n"); 
		str.append("$(\"#cboMessageBy\").change();\n"); 
		str.append("getMessageType("+ messageRecipientGroup +");\n");
		str.append("function getMessageType(code){\n"); 
		strHideToggle.append("function hideMessageTypeCheckboxPanel(){\n"); 
	    String ifFirstObject = "";
		for(DTOBase obj: messageTypeList){
			ifFirstObject = messageTypeList.get(0).getCode().equalsIgnoreCase(obj.getCode())?"":"else ";
	    	str.append( ifFirstObject + "if (code == \"" + obj.getCode() + "\") {\n");
		    str.append(" 	hideMessageTypeCheckboxPanel();\n");
		    str.append(" 	$(\"#div" + obj.getDisplayText().replace(" ", "") + "\").show();\n");
		    str.append(" 	$(\"#div" + obj.getCode() + "MEDIUM\").show();\n");
			str.append(" }");
		    strHideToggle.append(" $(\"#div" + obj.getDisplayText().replace(" ", "") +"\").hide();\n");
		    strHideToggle.append(" 	$(\"#div" + obj.getCode() + "MEDIUM\").hide();\n");
		}
		strHideToggle.append("  }\n");
	    str.append(" else{\n");
	    str.append(" 	hideMessageTypeCheckboxPanel();\n");
	    str.append(" }\n");
	    str.append(" }\n");
	    str.append(strHideToggle.toString());
	    str.append("</script>\n");
		}
	    return str.toString();
	}
	
	public static List<DTOBase> getSpecificMediumListObjectList(String[] mediumList){
		List<DTOBase> specificMediumList = new ArrayList<DTOBase>();
		String[] mediumListDisplayText = MessageDTO.SCHOOL_MESSAGE_MEDIUM_LIST;
		String[] mediumListCode = MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST;
		for(int x=0;x<mediumList.length;x++){
			DTOBase mediumDTO = new DTOBase();
			mediumDTO.setId(x);
			mediumDTO.setCode(mediumList[x]);
			String mediumDisplayText = mediumListDisplayText[StringUtil.getIndexFromArrayStr(mediumListCode,  mediumList[x])];
			if(mediumDisplayText.contains("CALENDAR")){
				mediumDisplayText = "CALENDAR";
			}
			mediumDTO.setDisplayText(mediumDisplayText);
			specificMediumList.add(mediumDTO);
		}
		return specificMediumList;
	}
	
	
	public static List<DTOBase> getRecipientListByMessageRecipientGroup(String messageRecipientGroup, String messageRecipientGroupCheckedCodes, List<DTOBase> academicProgramList, List<DTOBase> userList, List<DTOBase> studentList, List<DTOBase> teacherList){
		String[] messageRecipientGroupCodesArray = messageRecipientGroupCheckedCodes.split("~");
		List<DTOBase> newAcademicProgramByAcademicProgramGroupSubGroup = new ArrayList<DTOBase>();
		List<DTOBase> recipientList = new ArrayList<DTOBase>();
		for(int i=0;i<messageRecipientGroupCodesArray.length;i++){
			List<DTOBase> academicProgramByAcademicProgramGroupSubGroup = new ArrayList<DTOBase>();
			if(messageRecipientGroup.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[1])){
				academicProgramByAcademicProgramGroupSubGroup = AcademicProgramUtil.getAcademicProgramListByAcademicProgramGroupCode(academicProgramList, messageRecipientGroupCodesArray[i]);
			}else if(messageRecipientGroup.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[2])){
				academicProgramByAcademicProgramGroupSubGroup = AcademicProgramUtil.getAcademicProgramListByAcademicProgramSubgroupCode(academicProgramList, messageRecipientGroupCodesArray[i]);
			}
			newAcademicProgramByAcademicProgramGroupSubGroup.addAll(academicProgramByAcademicProgramGroupSubGroup);
		}
		for(DTOBase academicProgramObj: newAcademicProgramByAcademicProgramGroupSubGroup){
			AcademicProgramDTO academicProgram = (AcademicProgramDTO) academicProgramObj;
			List<DTOBase> studentListByAcademicProgram = StudentUtil.getStudentListByAcademicProgram(userList, StudentUtil.getStudentListByAcademicProgram(studentList, academicProgram.getCode()));
			List<DTOBase> teacherListByAcademicProgram = TeacherUtil.getTeacherListByAcademicProgram(userList, TeacherUtil.getTeacherListByAcademicProgram(teacherList, academicProgram.getCode()));
			recipientList.addAll(studentListByAcademicProgram);
			recipientList.addAll(teacherListByAcademicProgram);
		}
		return recipientList;
	}
	
	public static String getPaneledRecipientTable(SessionInfo sessionInfo, Pagination pagination, List<DTOBase> recipientList){
		StringBuffer str = new StringBuffer();
     	if(recipientList.size()>=1){
			str.append("<div id=\"pnlRecipientTable\" class=\"col-sm-12\">");
			str.append("<div class=\"panel panel-primary  m-t\">");
			str.append("<div class=\"panel-heading\"><strong>Recipients</strong></div>");
		    str.append("<div class=\"panel-body\">");
		    str.append("<table class=\"table table-striped\"><tbody>");
		    str.append("<thead><td>Name</td><td>CP#</td><td></td><tbody>");
		    for(DTOBase obj: recipientList){
		    	UserDTO user = (UserDTO) DTOUtil.getObjByCode(pagination.getRecordListUnfiltered(), obj.getCode());
		    	String cpNumber = StringUtil.isEmpty(user.getCpNumber())?"No Number":user.getCpNumber();
		    	String[] userPagination = new String[] {user.getName(true, false, false), "<span class=\"label label-primary\">" + cpNumber + "</span>", sessionInfo.isCurrentLinkDataEntry()?getDeleteRecipientButton(user.getCode()):""};
		    	str.append("<tr>");
		    	for(int i=0;i<userPagination.length;i++){
			    	str.append("<td>" + userPagination[i] + "</td>");
		    	}
		    	str.append("</tr>");
		    }
		    str.append("</tbody></table>");
		    str.append("</div></div></div>"); 
     	}
	    return str.toString();
	}
	
	public static List<DTOBase> getMessageListByCalendar(List<DTOBase> messageList){
		List<DTOBase> newList = new ArrayList<DTOBase>();
		for(DTOBase obj: messageList){
			MessageDTO message = (MessageDTO) obj;
			String[] messageTypeCodes = MessageUtil.getStrArrayValueArray(MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST, MessageDTO.SCHOOL_MESSAGE_MEDIUM_LIST, message.getMessageTypeCodes(), true);
			for(int x=0; x < messageTypeCodes.length; x++){
				if(messageTypeCodes[x].contains("CALENDAR")){
					newList.add(message);
				}
			}
		}
		return newList;
	}
	
	public static String getTextAriaForSMS(SessionInfo sessionInfo, String gridClass, MessageDTO message){
		StringBuffer str = new StringBuffer();
		str.append("<div class=\"" + gridClass + "\">");
		str.append("<strong>SMS</strong> <small><i>(Maximum of 160 characters)</i></small><br>");
		str.append("<textarea class=\"m-t-xs\" name=\"txaContentSMS\" id=\"txaContentSMS\" maxlength=\"160\" style=\"width:100%;height:70px;border-color:#dedede;\"" + (sessionInfo.isCurrentLinkDataEntry()?">":"disabled>") + message.getContentSMS() +"</textarea>");
		str.append("</div>");
		return str.toString();
	}
	
	public static String getSummerNoteTextEditor(SessionInfo sessionInfo, MessageDTO message){
		StringBuffer str = new StringBuffer();
		str.append("<input type=\"hidden\" name=\"txtContent\" id=\"txtContent\" />");
		if(!sessionInfo.isCurrentLinkAdd()){
			str.append("<div class=\"col-sm-12 no-padding m-t\"><strong>Content</strong><div class=\"col-sm-12\" style=\"background-color:#fdfcfc;border-radius:5px; padding:20px;border: 2px solid #f3f1f1;margin-top: 10px;\">" + message.getContent() + "</div></div>");
		} else {
			str.append("<div class=\"col-sm-12 m-t no-padding\">");
			str.append("	<div class=\"summernote\"></div>");
			str.append("	<div id=\"divCharacterCount\"><small>0 Characters <i>(Maximum of 160 characters for SMS)</i></small></div>");
			str.append("</div>");
		}

		str.append("<script>");
		str.append("  $(document).ready(function(){");
		str.append("    $('.summernote').summernote();");
		str.append("    $('.note-editable.panel-body').focus();");
		str.append("	$('.note-editable.panel-body').css(\"height\", \"400px\");");
		str.append("	$('.note-editable.panel-body').css(\"min-height\", \"400px\");");
		str.append("	$('body').on('DOMSubtreeModified', '.note-editable.panel-body', function() {");
		str.append("		$('#txtContent').val($('.note-editable.panel-body').html());");
		str.append("		$('textarea#txaContentSMS').text($('.note-editable.panel-body').text());");
		str.append("		$('#txtContentWebHeadline').val($('.note-editable.panel-body').text());");
		str.append("		$('#txtContentFaceKeeper').val($('.note-editable.panel-body').text());");
		str.append("		var message = $('textarea#txaContentSMS').text();");
		str.append("		$('#divCharacterCount').html('<small>' + message.length +' Characters <i>(Maximum of 160 characters for SMS)</i></small>')");
		str.append("	});");
		if(!message.getContent().isEmpty() && sessionInfo.isCurrentLinkDataEntry()){
			str.append("     	$('.note-editable.panel-body').html('" + message.getContent() +"');");
		}
		str.append("});");
		str.append("</script>");
		return str.toString();
	}
	
	
	public static String getAddRecipientButton(String userCode){
		StringBuffer str = new StringBuffer();
		str.append("<button class=\"btn btn-sm btn-primary pull-right m-t-n-xs\" onclick=\"messageRecipientAction('" + userCode + "', 'U00055')\" ><strong>Add</strong></button>");
		return str.toString();
	}
	
	public static String getDeleteRecipientButton(String userCode){
		StringBuffer str = new StringBuffer();
		str.append("<button class=\"btn btn-sm btn-danger pull-right m-t-n-xs\" onclick=\"messageRecipientAction('" + userCode + "','U00056')\" ><strong><i class=\"fa fa-trash-o\"></i></strong></button>");
		return str.toString();
	}
	
	public static List<DTOBase> getMessageTypeObjectList(String messageTypeCode){
		List<DTOBase> objectList = new ArrayList<DTOBase>();
		if(messageTypeCode.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[1])){
			objectList = new AcademicProgramGroupDAO().getAcademicProgramGroupList();
		}else if(messageTypeCode.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[2])){
			objectList = new AcademicProgramSubgroupDAO().getAcademicProgramSubgroupList();
		}else if(messageTypeCode.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[3])){
			objectList = new CourseDAO().getCourseList();
		}
		return objectList;
	}
	
	public static String[] getMessageTypeMediumList(String messageTypeCode){
		String[] mediumList = new String[] {};
		if(messageTypeCode.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0])){
			mediumList = MessageDTO.SCHOOL_MESSAGE_TYPE_INDIVIDUAL_MEDIUM_CODE_LIST;
		}else if(messageTypeCode.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[1])){
			mediumList = MessageDTO.SCHOOL_MESSAGE_TYPE_ACADEMIC_PROGRAM_GROUP_MEDIUM_CODE_LIST;
		}else if(messageTypeCode.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[2])){
			mediumList = MessageDTO.SCHOOL_MESSAGE_TYPE_ACADEMIC_PROGRAM_SUBGROUP_MEDIUM_CODE_LIST;
		}else if(messageTypeCode.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[3])){
			mediumList = MessageDTO.SCHOOL_MESSAGE_TYPE_COURSE_MEDIUM_CODE_LIST;
		}
		return mediumList;
	}

	public static void setUserRecipientPaginationRecord(SessionInfo sessionInfo, Pagination pagination) {
		for(DTOBase dto: pagination.getCurrentPageRecordList()) {
			UserDTO user = (UserDTO) dto;
			user.setPaginationRecord(new String[] {user.getLastName(), user.getFirstName(), user.getMiddleName(), user.getCpNumber(), getAddRecipientButton(user.getCode())});
		}
	}
}
