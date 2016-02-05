package org.ivan.MojRad.DaoClasses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorClass {

	private String error;
	private int errorCode;
	private String documentation;
	
	public ErrorClass(){
		
	}
	
	
	
	
	public ErrorClass(String error, int errorCode, String documentation) {
		super();
		this.error = error;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}




	public String getError() {
		
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	

}
