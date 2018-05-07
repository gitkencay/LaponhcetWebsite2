package com.laponhcet.action.message;

import java.util.List;
import java.util.regex.Pattern;

import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.StudentDTO;
import com.laponhcet.dto.TeacherDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.ActionResponse;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class MessageAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void setInput() {
		MessageDTO message = (MessageDTO) getSessionAttribute(MessageDTO.SESSION_MESSAGE);
		String messageRecipientGroup = getRequestString("cboMessageBy");
		List<DTOBase> academicProgramList = (List<DTOBase>) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST);
		List<DTOBase> userList = (List<DTOBase>) getSessionAttribute(UserDTO.SESSION_USER_LIST);
		List<DTOBase> studentList = (List<DTOBase>) getSessionAttribute(StudentDTO.SESSION_STUDENT_LIST);
		List<DTOBase> teacherList = (List<DTOBase>) getSessionAttribute(TeacherDTO.SESSION_TEACHER_LIST);
		List<DTOBase> objectList = MessageUtil.getMessageTypeObjectList(messageRecipientGroup);
		String[] mediumListArray = MessageUtil.getMessageTypeMediumList(messageRecipientGroup);
		List<DTOBase> mediumList = MessageUtil.getSpecificMediumListObjectList(mediumListArray);
		message.setContent(getRequestString("txtContent", true));
		message.setPriority(Integer.valueOf(getRequestString("cboPriority")));
		message.setValidTimestampStart(DateTimeUtil.getStrToDateTime(getRequestString("txtValidTimestampStart"), "MM/dd/yyyy"));
		message.setValidTimestampEnd(DateTimeUtil.getStrToDateTime(getRequestString("txtValidTimestampEnd"), "MM/dd/yyyy"));
		String selectedMediumCodes = "";
		String selectedRecipientGroupCheckCodes = "";
		if(objectList.size()!=0){
			selectedRecipientGroupCheckCodes = getSelectedCheckBox(objectList, MessageUtil.getStrArrayValue(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST, MessageDTO.SCHOOL_MESSAGE_TYPE_LIST, messageRecipientGroup, true).replace(" ", ""));
			selectedRecipientGroupCheckCodes = StringUtil.isEmpty(selectedRecipientGroupCheckCodes)?" ":selectedRecipientGroupCheckCodes;
		}
		if(mediumList.size()!=0){
			selectedMediumCodes = getSelectedCheckBox(mediumList, messageRecipientGroup+"MEDIUM");
			selectedMediumCodes = StringUtil.isEmpty(selectedMediumCodes)?" ":selectedMediumCodes;
		}
		message.setMessageTypeCodes(messageRecipientGroup + "|"+ selectedRecipientGroupCheckCodes + "|" + selectedMediumCodes);
		message.setContentSMS(getRequestString("txaContentSMS", true));
		message.setContentWebHeadline(getRequestString("txtContentWebHeading", true));
		message.setContentFaceKeeper(getRequestString("txtContentFaceKeeper", true));

		if(!messageRecipientGroup.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0]) && !sessionInfo.getCurrentLink().getCode().equalsIgnoreCase("U00055") && !sessionInfo.getCurrentLink().getCode().equalsIgnoreCase("U00056")){
			String[] messageTypeCodes = message.getMessageTypeCodes().split(Pattern.quote("|"));
			message.setRecipientList(MessageUtil.getRecipientListByMessageRecipientGroup(messageRecipientGroup, messageTypeCodes[1], academicProgramList, userList, studentList, teacherList));
		}
	}
	
	protected void validateInput() {
		if(sessionInfo.isCurrentLinkAddSubmit() || sessionInfo.isCurrentLinkUpdateSubmit()){
			MessageDTO message = (MessageDTO) getSessionAttribute(MessageDTO.SESSION_MESSAGE);
			String[] messageTypeCodes = message.getMessageTypeCodes().split(Pattern.quote("|"));
			if(message.getContentSMS().length() > 0){
				if(StringUtil.isStrExistInStrArr(messageTypeCodes[2].split("~"), MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST[0]) && message.getContentFaceKeeper().length()>40){
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Message Length! FaceKeepeer message must only contain 45 characters.");
				}else if(StringUtil.isStrExistInStrArr(messageTypeCodes[2].split("~"), MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST[1]) && message.getContentSMS().length()>160){
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Message Length! SMS message must only contain 160 characters.");
				}else if(StringUtil.isStrExistInStrArr(messageTypeCodes[2].split("~"), MessageDTO.SCHOOL_MESSAGE_MEDIUM_CODE_LIST[3]) && message.getContentWebHeadline().length()>100){
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Message Length! Headline message must only contain 100 characters.");
				}else if(!DateTimeUtil.isValidDateTime(DateTimeUtil.getDateTimeToStr(message.getValidTimestampStart(), "MM/dd/yyyy"), "MM/dd/yyyy")){
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Message Start Date. Please recheck Start Date.");
				}else if(!DateTimeUtil.isValidDateTime(DateTimeUtil.getDateTimeToStr(message.getValidTimestampEnd(), "MM/dd/yyyy"), "MM/dd/yyyy")){
					actionResponse.constructMessage(ActionResponse.TYPE_INVALID, "Message End Date. Please recheck End Date.");
				}else if(!StringUtil.isEmpty(message.getMessageTypeCodes())){
					if(StringUtil.isEmpty(messageTypeCodes[2])){
						actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Medium. Please choose at least one (1) Medium.!");
					}else if(!messageTypeCodes[0].equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0]) && StringUtil.isEmpty(messageTypeCodes[1])){
						actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Recipient Group. Please choose at least one (1) Recipient Group.!");
					}else if(messageTypeCodes[0].equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0]) && message.getRecipientList().size()==0){
						actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Recipient. There is no recipient in selected group!");
					}else if(!messageTypeCodes[0].equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0]) && message.getRecipientList().size()==0){
						actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Recipient Group. Please choose at least one (1) Recipient Group.!");
					}			
				}
			}else if(message.getContentSMS().length() == 0){
				actionResponse.constructMessage(ActionResponse.TYPE_EMPTY, "Message Content");
			}
		}
	}
}
