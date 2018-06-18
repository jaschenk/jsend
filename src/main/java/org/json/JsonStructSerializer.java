package org.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;


/**
 * Class for Serializing JSend structure as JSON.
 * You don't need to call serialize method, it's invoked by Jackson library
 * Examples in Junit test package
 * @see "http://labs.omniti.com/labs/jsend"
 * @author David Bayo
 *
 */
public class JsonStructSerializer extends JsonSerializer<JsonStruct> {

	@Override
	public void serialize(JsonStruct arg0, JsonGenerator arg1,
			SerializerProvider arg2) throws IOException {
		arg1.writeStartObject();
		JsonStatusEnum status = arg0.getStatus();
		
		if (status != null) {
			arg1.writeFieldName(JsonStruct.ELEMENT_NAME_STATUS);
			arg1.writeString(status.toString());
		} else {
			throw new IOException(JsonStruct.ELEMENT_NAME_STATUS + " field is mandatory");
		}

		if (status == JsonStatusEnum.SUCCESS) {
			if (arg0.getData() != null) {
				writeDataObject(arg1, arg0.getData());
			} else {
				throw new IOException(JsonStruct.ELEMENT_NAME_DATA
						+ " field is mandatory when " + JsonStruct.ELEMENT_NAME_STATUS
						+ " is " + JsonStatusEnum.SUCCESS);
			}
		}
		
		if (status == JsonStatusEnum.FAIL) {
			if (arg0.getData() != null) {
				writeDataObject(arg1, arg0.getData());
			} else {
				throw new IOException(JsonStruct.ELEMENT_NAME_DATA
						+ " field is mandatory when " + JsonStruct.ELEMENT_NAME_STATUS
						+ " is " + JsonStatusEnum.FAIL);
			}
		}

		if (status == JsonStatusEnum.ERROR) {
			if (arg0.getMessages() != null && !arg0.getMessages().isEmpty()) {
				arg1.writeFieldName(JsonStruct.ELEMENT_NAME_MESSAGES);
				arg1.writeString(arg0.getMessages().get(0));               // FIX Me... We really do not need all of this...
				if (arg0.getCode() != null) {
					arg1.writeFieldName(JsonStruct.ELEMENT_NAME_CODE);
					arg1.writeString(arg0.getCode());
				}
				if (arg0.getData() != null) {
					writeDataObject(arg1, arg0.getData());
				}
			} else {
				throw new IOException(JsonStruct.ELEMENT_NAME_MESSAGES
						+ " field is mandatory when " + JsonStruct.ELEMENT_NAME_STATUS
						+ " is " + JsonStatusEnum.ERROR);
			}
		}
		arg1.writeEndObject();
	}

	private void writeDataObject(JsonGenerator arg1, JsonData data) throws IOException {
		arg1.writeFieldName(JsonStruct.ELEMENT_NAME_DATA);
		arg1.writeStartObject();
		Map<String, Object> map = data.getKeysValuesMap();
		
		for (Map.Entry<String, Object> entry : map.entrySet())
		{
			arg1.writeFieldName(entry.getKey());
			arg1.writeObject(entry.getValue());
		}
		arg1.writeEndObject();
	}

}
