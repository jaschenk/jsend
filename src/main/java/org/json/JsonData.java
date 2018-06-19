package org.json;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class for creating JSend data key/s-value/s
 * Instantiate this class and put key-value or key-values in case of arrays
 * Examples in Junit test package
 * @see "http://labs.omniti.com/labs/jsend"
 * @author David Bayo
 *
 * Modified to preserve order as keys add to Root Map
 * @author schenkje
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonData {

	@JsonProperty("data")
	private Map<String, Object> keysValuesMap = new LinkedHashMap<>();
	
	public Map<String, Object> getKeysValuesMap() {
		return keysValuesMap;
	}

	public void put(String key, Object value) {
		keysValuesMap.put(key, value);
	}
}
