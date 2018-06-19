package org.json;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for creating JSend structure
 * Instantiate this class, set one of the different status and set content created with JsonContent class
 * Examples in Junit test package
 * @see "http://labs.omniti.com/labs/jsend"
 * @author David Bayo
 *
 * Remove Custom Serializer in favor of Annotations to derive rendering.
 * Yes, I have removed requirements based upon the 'status' field, certain fields should be or should not be set
 * and thhis version of the implementation foregoes those rules for flexibility.
 * @author schenkje
 *
 */
@JsonPropertyOrder({ "auditId", "requestTime", "responseTime", "requestURL", "serviceName", "version",
		"status", "messages", "errorConditionCode", "content"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonStruct implements Serializable {
	/**
	 * Optional Request Audit Id
	 */
	private String auditId;
	/**
	 * Incoming Request Time.
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="US/Pacific")
	private Date requestTime;
	/**
	 * Outgoing Response Time.
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="US/Pacific")
	private Date responseTime;
	/**
	 * Original Request URI
	 */
	private String requestURI;
	/**
	 * Service Name
	 */
	private String serviceName;
	/**
	 * API Version being applied
	 */
	private String version;
	/**
	 * Json Status Enum
	 */
	private JsonStatus status = null;
	/**
	 * Messages,
	 * Updated to enable ability to have more than one message.
	 */
	private List<String> messages = null;
	/**
	 * Error Condition Code
	 */
	private String errorConditionCode = null;
	/**
	 * Json Data Content of Response, if Applicable.
	 */
	private JsonContent content;

	/**
	 * Helper Method to Set Status to Success
	 */
	public void setStatusToSuccess() {
		this.setStatus(JsonStatus.SUCCESS);
	}

	/**
	 * Helper Method to Set Status to Fail
	 */
	public void setStatusToFail() {
		this.setStatus(JsonStatus.FAIL);
	}

	/**
	 * Helper Method to Set Status to Error
	 */
	public void setStatusToError() {
		this.setStatus(JsonStatus.ERROR);
	}

	/**
	 * Helper method to determine if Status was Success.
	 * @return boolean indicates if Status was Success or Not.
	 */
	@JsonIgnore
	public boolean isSuccess() {
		return this.status == JsonStatus.SUCCESS;
	}

	/**
	 * Helper method to determine if Status was Fail.
	 * @return boolean indicates if Status was Fail or Not.
	 */
	@JsonIgnore
	public boolean isFail() {
		return this.status == JsonStatus.FAIL;
	}

	/**
	 * Helper method to determine if Status was Error.
	 * @return boolean indicates if Status was Error or Not.
	 */
	@JsonIgnore
	public boolean isError() {
		return this.status == JsonStatus.ERROR;
	}

	/**
	 * Optional audit Identifier
	 * @return String containing auditId or Null.
	 */
	public String getAuditId() {
		return auditId;
	}

	/**
	 * Set the Optional AuditId
	 * @param auditId Reference
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	/**
	 * Request Time
	 * @return Date
	 */
	public Date getRequestTime() {
		return requestTime;
	}

	/**
	 * Set Request Time
	 * @param requestTime Date
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * Get Response Time
	 * @return Date
	 */
	public Date getResponseTime() {
		return responseTime;
	}

	/**
	 * Set the Response Time.
	 * @param responseTime Date
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	/**
	 * Set Original Request URI
	 * @return String containing original request URI Path
	 */
	public String getRequestURI() {
		return requestURI;
	}

	/**
	 * Set Original Request URI
	 * @param requestURI - String of URI
	 */
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	/**
	 * Optional Service Name
	 * @return String - Service Name
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Optional Service Name
	 * @param serviceName String representing a Service Name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Optional API Version
	 * @return String of API Version, if applicable
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Optional SET API Version of Request
	 * @param version String of version to be set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Overall Completion Status of this Reques
	 * @return JsonStatus Reference
	 */
	public JsonStatus getStatus() {
		return status;
	}

	/**
	 * Set Status
	 * @param status to be set
	 */
	public void setStatus(JsonStatus status) {
		this.status = status;
	}

	/**
	 * Get Messages
	 * @return List<String> of Messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * Set Messages
	 * @param messages Reference of Messages List
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * Helper Method to Add a new Message
	 * @param message to be Added to construct
	 */
	public void addMessage(String message) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(message);
	}

	/**
	 * Optional Error Condition Code
	 * @return String containing additional Error Information
	 */
	public String getErrorConditionCode() {
		return errorConditionCode;
	}
	/**
	 * Optional Error Condition Code
	 * @param errorConditionCode  String containing additional Error Information
	 */
	public void setErrorConditionCode(String errorConditionCode) {
		this.errorConditionCode = errorConditionCode;
	}

	/**
	 * Content of Request
	 * @return JsonContent Reference
	 */
	@JsonUnwrapped
	public JsonContent getContent() {
		return content;
	}

	/**
	 * Set Content as applicable
	 * @param content reference to be established
	 */
	public void setContent(JsonContent content) {
		this.content = content;
	}
}
