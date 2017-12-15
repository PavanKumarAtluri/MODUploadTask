package com.mod.healthrecords.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper mapper = null;
	static {
		mapper = new ObjectMapper();
	}

	public static String javaToJson(Object obj) {

		String val = "";
		try {
			val = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return val;
	}

	public static <T> T jsonToJava(String json, Class<T> clazz) {
		T ret = null;
		try {
			ret = mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
