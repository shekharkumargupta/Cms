package com.opgea.cms.domain.modal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;

public class JsonModel<T> {
    /*
	private static final Logger log = LoggerFactory.getLogger(JsonModel.class); 
	public enum ObjectState {
		SUCCESS("success"),ERROR("error"),NOT_SET("not_set");
		 private String state;

		ObjectState(String state){
			 this.state=state;
		}
		
		public String value(){return this.state;}
		
	}

	protected Map<String,Object> hash = new HashMap<String, Object>();
	public JsonModel(T t){
		addData(t);
	}
	
	public JsonModel<T> addData(String data){
		hash.put("data", data);
		return this;
	}
	
	public JsonModel<T> addResult(String result){
		
		hash.put("result",result);
		return this;
	}
	public JsonModel<T> addToData(String key, Object obj){
		
		@SuppressWarnings("unchecked")
		Map<String, Object> data = (Map<String, Object>)hash.get("data");
		if(data==null||data.isEmpty()){
			data = new HashMap<String, Object>();
			hash.put("data", data);
		}
		data.put(key, obj);
		return this;
	}
	public JsonModel<T> addData(T t){
		hash.put("data",t);
		return this;
	}

	public JsonModel<T> state(JsonModel.ObjectState objectState){
		switch (objectState) {
		case ERROR:
			hash.put("error", true);
			hash.remove("success");
			break;
		case SUCCESS:
			hash.put("success", true);
			hash.remove("error");
			break;
		default:
			hash.remove("success");
			hash.remove("error");
			break;
		}
		return this;
	}
	
	public JsonModel<T> successState(){
		
		return state(ObjectState.SUCCESS);
	}
	
	public JsonModel<T> errorState(){
		
		return state(ObjectState.ERROR);
	}
	
	public JsonModel(){}

	public Map<String, Object> getHash() {
		return hash;
	}

	public Map<String, Object> hash() {return getHash();}
	
	public void setHash(Map<String, Object> hash) {
		this.hash = hash;
	}
	
	public JsonModel<T> addConstraintViolations(Set<ConstraintViolation<T>> violations){
		
		Map<String, String> errorMessages = new HashMap<String, String>();
		for (ConstraintViolation<T> violation : violations) {
			log.debug("Error Code:{} Message:{}",violation.getPropertyPath().toString(),
					violation.getMessage());
			errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		addErrors(errorMessages);
		
		return this;
		
	}
	
	public JsonModel<T> addSingleError(String message){
		hash.put("error_message", message);
		errorState();
		return this;
	}
	
	public JsonModel<T> addErrors(Map<String,String> errors){
		
		if(!errors.containsKey("errors")){
			
			hash.put("errors", errors);
		}else{
			//TODO:MAY NEED SOME KIND OF KEY PREFIX FOR KEYS MAY COLLIDE
			@SuppressWarnings("unchecked")
			Map<String,String> errorsMap = (Map<String,String>)hash.get("errors");
			errorsMap.putAll(errors);
		}
		errorState();
		return this;
		
	}

	public JsonModel<T> addObjectErrors(List<ObjectError> failures) {

		Map<String, String> errorMessages = new HashMap<String, String>();
		  for(ObjectError error : failures){
			  log.debug("Error Code:{} Message:{}",error.getCode(),error.getDefaultMessage());
			  errorMessages.put(error.getCode(), error.getDefaultMessage());
	      }
	      
		  addErrors(errorMessages);  
		return this;		

		
	} 
	
*/	
}
