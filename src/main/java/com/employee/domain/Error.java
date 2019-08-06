package com.employee.domain;

public class Error {
	
	String createError="not created";
	String updateError="could not update employee";
	String pathError="incorrect path specified";
	
	public String getCreateError()
	{
		return createError;
	}
	
	public String getUpdateError()
	{
		return updateError;
	}
	
	public String getPathError()
	{
		return pathError;
	}

}
