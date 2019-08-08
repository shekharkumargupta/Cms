package com.opgea.cms.domain.modal;

import java.util.HashMap;
import java.util.Map;


public class JsonModelMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1934928348234876L;

	public enum Result {
		SUCCESS("success"), FAILURE("failure");
		private java.lang.String messageKey;
		Result(java.lang.String message) { this.messageKey = message; }
		public String toString() { return this.messageKey; }
	}
	
	private static JsonModelMap<String, Object> defaultMapMessage(Result result, String message) {
		JsonModelMap<String, Object> map = new JsonModelMap<String, Object> ();
		map.put(result.toString(), message);
		return map;	
	}
	private static JsonModelMap<String, Object> defaultMap(Result result) {
		return defaultMapMessage(result, result.toString());

	}
	public static JsonModelMap<String, Object> success() {
		return defaultMap(Result.SUCCESS);
	}
	public static JsonModelMap<String, Object> successWithMessage(String message) {
		return defaultMapMessage(Result.SUCCESS, message);
	}	
	public static JsonModelMap<String, Object> failure() {
		return defaultMap(Result.FAILURE);
	}
	public static JsonModelMap<String, Object> failWithMessage(String message) {
		return defaultMapMessage(Result.FAILURE, message);
	}
	
	@SuppressWarnings("unchecked")
	public JsonModelMap<K, V> data(Object val) { 
		this.put((K)"data", (V)val);
		return this;
	}

	@SuppressWarnings("unchecked")
	public Map<K, V> add(String key, Object value) {
		this.put((K)key, (V)value);
		return this;
	}
}
