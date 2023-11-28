package com.community.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.community.dto.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		
		String beanName = errors.getObjectName();
		
		if(beanName.equals("joinUser") || beanName.equals("modifyUser")) {
			if(!user.getUser_pw().equals(user.getUser_pw2())) {
				errors.rejectValue("user_pw", "PwNotEquals");
				errors.rejectValue("user_pw2", "PwNotEquals");
			}
		}
		if(beanName.equals("joinUser")) {
			if(user.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
	}
 
}
