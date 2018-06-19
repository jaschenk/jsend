package org.json;


import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum for JSend Status representation
 * Examples in Junit test package
 * @see "http://labs.omniti.com/labs/jsend"
 * @author David Bayo
 *
 */
public enum JsonStatus {

	SUCCESS("success"), FAIL("fail"), ERROR("error");
	private final String status;

	JsonStatus(final String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.status.toLowerCase();
	}
}