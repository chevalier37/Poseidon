package com.poseidon.business;

import java.util.regex.Pattern;

public class CheckPassword {
	
	private static final Pattern [] passwordRegexes = new Pattern[3];
    {
        passwordRegexes[0] = Pattern.compile(".*[A-Z].*");
        passwordRegexes[1] = Pattern.compile(".*[a-z].*");
        passwordRegexes[2] = Pattern.compile(".*[~!@#$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*");
    }
    
    public static boolean isLegalPassword(String pass) {
    	
    	if(pass.length() < 8) {
    		return false;
    	}

        for(int i = 0; i < passwordRegexes.length; i++){
            if(!passwordRegexes[i].matcher(pass).matches())
                return false;
        }
        return true;
    }

}
