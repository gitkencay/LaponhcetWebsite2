package com.laponhcet.util;

import java.io.Serializable;

import com.laponhcet.dto.UserRFIDDTO;
import com.mytechnopal.dto.UserDTO;

public class UserRFIDUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static UserRFIDDTO getUserRFIDByUser(UserDTO user) {
		UserRFIDDTO userRFID = new UserRFIDDTO();
		userRFID.setCode(user.getCode());
		userRFID.setProfilePict(user.getProfilePict().getPict());
		userRFID.setRfid(user.getRfid());
		userRFID.setLastName(user.getLastName());
		userRFID.setFirstName(user.getFirstName());
		userRFID.setMiddleName(user.getMiddleName());
		userRFID.setPrefixName(user.getPrefixName());
		userRFID.setSuffixName(user.getSuffixName());
		userRFID.setOtherTitle(user.getOtherTitle());
		userRFID.setGender(user.getGender());
		userRFID.setCpNumber(user.getCpNumber());
		userRFID.setContactCPNumber(user.getContactCPNumber());
		userRFID.setAddedBy(user.getAddedBy());
		userRFID.setAddedTimestamp(user.getAddedTimestamp());
		userRFID.setUpdatedBy(user.getUpdatedBy());
		userRFID.setUpdatedTimestamp(user.getUpdatedTimestamp());
		return userRFID;
	}
}
