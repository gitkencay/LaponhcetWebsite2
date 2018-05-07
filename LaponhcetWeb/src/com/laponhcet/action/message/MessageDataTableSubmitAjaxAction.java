package com.laponhcet.action.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.laponhcet.dao.MessageDAO;
import com.laponhcet.dto.MessageAcademicProgramGroupDTO;
import com.laponhcet.dto.MessageAcademicProgramSubgroupDTO;
import com.laponhcet.dto.MessageCourseDTO;
import com.laponhcet.dto.MessageDTO;
import com.laponhcet.dto.MessageIndividualDTO;
import com.laponhcet.util.MessageUtil;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.AjaxActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.mytechnopal.util.StringUtil;

public class MessageDataTableSubmitAjaxAction extends AjaxActionBase {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void searchRecord() {
		Pagination pagination = (Pagination) getSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION);
		String searchCriteria = getRequestString("cboSearchCriteria");
		String searchValue = getRequestString("txtSearchValue");
		List<DTOBase> messageIndividualList = (List<DTOBase>) getSessionAttribute(MessageIndividualDTO.SESSION_MESSAGE_INDIVIDUAL_LIST);
		List<DTOBase> messageAcademicProgramGroupList = (List<DTOBase>) getSessionAttribute(MessageAcademicProgramGroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_GROUP_LIST);
		List<DTOBase> messageAcademicProgramSubgroupList = (List<DTOBase>) getSessionAttribute(MessageAcademicProgramSubgroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_SUBGROUP_LIST);
		List<DTOBase> messageCourseList = (List<DTOBase>) getSessionAttribute(MessageCourseDTO.SESSION_MESSAGE_COURSE_LIST);
		List<DTOBase> newMessageList = new ArrayList<DTOBase>();
		if(StringUtil.isEmpty(searchValue)) {
			newMessageList = pagination.getRecordListUnfiltered();
		}else{
			if(searchCriteria.equalsIgnoreCase(MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[0])){
				newMessageList = new MessageDAO().getMessageListSearchByContent(searchValue);
			}else if(searchCriteria.equalsIgnoreCase(MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[1])){
				if(searchValue.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[0])){
					for(DTOBase obj: messageIndividualList){
						MessageIndividualDTO messageIndividual = (MessageIndividualDTO) obj;
						MessageDTO message = (MessageDTO) DTOUtil.getObjByCode(pagination.getRecordListUnfiltered(), messageIndividual.getMessageCode());
						if(message!=null && DTOUtil.getObjByCode(newMessageList, message.getCode()) == null){
							newMessageList.add(message);
						}
					}
				}else if(searchValue.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[1])){
					for(DTOBase obj: messageAcademicProgramGroupList){
						MessageAcademicProgramGroupDTO messageAcademicProgramGroupDTO = (MessageAcademicProgramGroupDTO) obj;
						MessageDTO message = (MessageDTO) DTOUtil.getObjByCode(pagination.getRecordListUnfiltered(), messageAcademicProgramGroupDTO.getMessage().getCode());
						if(message!=null && DTOUtil.getObjByCode(newMessageList, message.getCode()) == null){
							newMessageList.add(message);
						}
					}
				}else if(searchValue.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[2])){
					for(DTOBase obj: messageAcademicProgramSubgroupList){
						MessageAcademicProgramSubgroupDTO messageAcademicProgramSubgroupDTO = (MessageAcademicProgramSubgroupDTO) obj;
						MessageDTO message = (MessageDTO) DTOUtil.getObjByCode(pagination.getRecordListUnfiltered(), messageAcademicProgramSubgroupDTO.getMessage().getCode());
						if(message!=null && DTOUtil.getObjByCode(newMessageList, message.getCode()) == null){
							newMessageList.add(message);
						}
					}
				}else if(searchValue.equalsIgnoreCase(MessageDTO.SCHOOL_MESSAGE_TYPE_CODE_LIST[3])){
					for(DTOBase obj: messageCourseList){
						MessageCourseDTO messageCourseDTO = (MessageCourseDTO) obj;
						MessageDTO message = (MessageDTO) DTOUtil.getObjByCode(pagination.getRecordListUnfiltered(), messageCourseDTO.getMessage().getCode());
						if(message!=null && DTOUtil.getObjByCode(newMessageList, message.getCode()) == null){
							newMessageList.add(message);
						}
					}
				}
			}else if(searchCriteria.equalsIgnoreCase(MessageDTO.PAGINATION_SEARCH_CRITERIA_LIST[2])){
				for(DTOBase messageObj: pagination.getRecordListUnfiltered()){
					MessageDTO message = (MessageDTO) messageObj;
					if(StringUtil.isStrExistInStrArr(message.getMessageTypeCodes().split("~"), searchValue)){
						newMessageList.add(message);
					}
				}
			}
		}
		pagination.setRecordList(newMessageList);
	}
	
	@SuppressWarnings("unchecked")
	protected void setPaginationList() {
		Pagination pagination = (Pagination) getSessionAttribute(MessageDTO.SESSION_MESSAGE_PAGINATION);
		int currentPageTotalRecord = pagination.getCurrentPageRecordList().size();
		List<DTOBase> messageIndividualList = (List<DTOBase>) getSessionAttribute(MessageIndividualDTO.SESSION_MESSAGE_INDIVIDUAL_LIST);
		List<DTOBase> messageAcademicProgramGroupList = (List<DTOBase>) getSessionAttribute(MessageAcademicProgramGroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_GROUP_LIST);
		List<DTOBase> messageAcademicProgramSubgroupList = (List<DTOBase>) getSessionAttribute(MessageAcademicProgramSubgroupDTO.SESSION_MESSAGE_ACADEMIC_PROGRAM_SUBGROUP_LIST);
		List<DTOBase> messageCourseList = (List<DTOBase>) getSessionAttribute(MessageCourseDTO.SESSION_MESSAGE_COURSE_LIST);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("totalRecord", pagination.getRecordList().size());
			jsonObj.put("totalPage", pagination.getTotalPage());
			jsonObj.put("currentPage", pagination.getCurrentPage());
			jsonObj.put("recordPerPage", pagination.getRecordPerPage());
			jsonObj.put("currentPageTotalRecord", currentPageTotalRecord);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<currentPageTotalRecord; i++) {
			MessageDTO message = (MessageDTO) pagination.getCurrentPageRecordList().get(i);
			try {
				JSONObject jsonObjDetails = new JSONObject();
				jsonObjDetails.put("code", message.getCode());
				jsonObjDetails.put("type", MessageUtil.getMessageTypeCodesHTML(message, messageIndividualList, messageAcademicProgramGroupList, messageAcademicProgramSubgroupList, messageCourseList));
				jsonObjDetails.put("message", message.getContent());
				jsonArray.put(jsonObjDetails);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			jsonObj.put("details", jsonArray);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			response.getWriter().print(jsonObj);
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
