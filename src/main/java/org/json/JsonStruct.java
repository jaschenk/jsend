package org.json;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating JSend structure
 * Instantiate this class, set one of the different status and set data created with JsonData class
 * Examples in Junit test package
 * @see "http://labs.omniti.com/labs/jsend"
 * @author David Bayo
 *
 * Modified the Contants Name for proper consistency.
 * @author schenkje
 *
 */
@JsonSerialize(using = JsonStructSerializer.class)
public class JsonStruct {
	/**
	 * Constants
	 */
	public static final String ELEMENT_NAME_STATUS = "status";
	public static final String ELEMENT_NAME_DATA = "data";
	public static final String ELEMENT_NAME_MESSAGES = "messages";
	public static final String ELEMENT_NAME_CODE = "code";

	/**
	 * Elements making up the JsonStruct for Response.
	 */
	private JsonStatusEnum status = null;
	private List<String> messages = new ArrayList<String>();
	private String code = null;
	private JsonData data;

	/**
	 * Helper Method to Set Status to Success
	 */
	public void setStatusToSuccess() {
		this.setStatus(JsonStatusEnum.SUCCESS);
	}

	/**
	 * Helper Method to Set Status to Fail
	 */
	public void setStatusToFail() {
		this.setStatus(JsonStatusEnum.FAIL);
	}

	/**
	 * Helper Method to Set Status to Error
	 */
	public void setStatusToError() {
		this.setStatus(JsonStatusEnum.ERROR);
	}

	/**
	 * Helper method to determine if Status was Success.
	 * @return boolean indicates if Status was Success or Not.
	 */
	public boolean isSuccess() {
		return this.status == JsonStatusEnum.SUCCESS;
	}

	/**
	 * Helper method to determine if Status was Fail.
	 * @return boolean indicates if Status was Fail or Not.
	 */
	public boolean isFail() {
		return this.status == JsonStatusEnum.FAIL;
	}

	/**
	 * Helper method to determine if Status was Error.
	 * @return boolean indicates if Status was Error or Not.
	 */
	public boolean isError() {
		return this.status == JsonStatusEnum.ERROR;
	}

	public JsonStatusEnum getStatus() {
		return status;
	}

	public void setStatus(JsonStatusEnum status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public void addMessage(String message) {
		this.messages.add(message);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public JsonData getData() {
		return data;
	}

	public void setData(JsonData data) {
		this.data = data;
	}
}
