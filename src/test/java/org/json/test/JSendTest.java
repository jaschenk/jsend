package org.json.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JsonData;
import org.json.JsonStruct;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSendTest {
	private static final String ENCODING_UTF_8 = "UTF-8";

	/**
	 * Test for one key, one set of values in JSend representation.
	 * Output {"status":"success","data":{"numbers":{"two":"2","one":"1","three":"3"}}}
	 * @see "http://labs.omniti.com/labs/jsend"
	 * @author David Bayo
	 *
	 */
	@Test
	public void testSuccessOneItem() throws IOException {
		System.out.println("testSuccessOneItem");
		JsonStruct struct = new JsonStruct();
		JsonData data = new JsonData();
		Map<String, String> oneRowMultipleValue = new LinkedHashMap<>();
		oneRowMultipleValue.put("two", "2");
		oneRowMultipleValue.put("one", "1");
		oneRowMultipleValue.put("three", "3");

		data.put("numbers", oneRowMultipleValue);
		struct.setStatusToSuccess();
		struct.setData(data);
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// convert user object to json string
		mapper.writeValue(out, struct);
		System.out.println(out);
		//Remove double quotes to see unescaped JSON
		String expectedJSON = "{\"status\":\"success\",\"data\":{\"numbers\":{\"two\":\"2\",\"one\":\"1\",\"three\":\"3\"}}}";
		assertEquals(expectedJSON, out.toString(JSendTest.ENCODING_UTF_8));
	}
	
	/**
	 * Test for one key, one array of set of values in JSend representation
	 * Output {"status":"success","data":{"numbers_array":[{"two":"2","one":"1","three":"3"},{"two":"2","one":"1","three":"3"}]}}
	 * @see "http://labs.omniti.com/labs/jsend"
	 * @author David Bayo
	 *
	 */
	@Test
	public void testSuccessItemArray() throws IOException {
		System.out.println("testSuccessItemArray");
		JsonStruct struct = new JsonStruct();
		JsonData data = new JsonData();
		Map<String, String> oneRowMultipleValue = new LinkedHashMap<>();
		oneRowMultipleValue.put("one", "1");
		oneRowMultipleValue.put("two", "2");
		oneRowMultipleValue.put("three", "3");
		
		List<Map<String, String>> list = new ArrayList<>();
		list.add(oneRowMultipleValue);
		list.add(oneRowMultipleValue);

		data.put("numbers_array", list);
		struct.setStatusToSuccess();
		struct.setData(data);
		
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// convert user object to json string
		mapper.writeValue(out, struct);
		System.out.println(out);
		//Remove double quotes to see unescaped JSON
		String expectedJSON = "{\"status\":\"success\",\"data\":{\"numbers_array\":[{\"one\":\"1\",\"two\":\"2\",\"three\":\"3\"},{\"one\":\"1\",\"two\":\"2\",\"three\":\"3\"}]}}";
		assertEquals(expectedJSON, out.toString(JSendTest.ENCODING_UTF_8));
	}

	@Test
	public void testAdditionalFields() throws IOException {
		System.out.println("testAdditionalFields");
		JsonStruct struct = new JsonStruct();
		struct.setAuditId(UUID.randomUUID().toString());
		struct.setRequestTime(new Date());
		struct.setConditionCode("something");

		JsonData data = new JsonData();
		Map<String, String> oneRowMultipleValue = new LinkedHashMap<>();
		oneRowMultipleValue.put("one", "1");
		oneRowMultipleValue.put("two", "2");
		oneRowMultipleValue.put("three", "3");

		List<Map<String, String>> list = new ArrayList<>();
		list.add(oneRowMultipleValue);

		data.put("foobar", list);
		struct.setStatusToFail();
		struct.setData(data);

		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// convert user object to json string
		mapper.writeValue(out, struct);
		System.out.println(out);
    }
}
