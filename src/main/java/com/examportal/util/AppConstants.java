package com.examportal.util;

public class AppConstants {
	public static final String USER_NAME_REGEX = "^[a-zA-Z][\sa-zA-Z]+$";
    public static final String MOBILE_NUMBER_REGEX = "^[6789][0-9]{9}$";

    // Exception messages
    public static final String INVALID_USER_NAME = "Invalid user name";
    public static final String INVALID_MOBILE_NUMBER = "Invalid mobile number";
    public static final String USER_NOT_FOUND = "User not found for email: %s";
    
    // Update exception message
    public static final String USER_UPDATE_EXCEPTION = "User update failed";

}
