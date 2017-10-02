package com.tsystems.ecare.validator;

import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//@Component
//public class UserValidator implements Validator{
//
//    @Autowired
//    private UserService userService;
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return User.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        User user = (User) o;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
//        if (user.getUsername().length() < 6 || user.getUsername().length() > 32 ) {
//            errors.rejectValue("username", "Size.loginForm.username");
//        }
//        if (userService.findByUsername(user.getUsername())!= null){
//            errors.rejectValue("username", "Duplicate.userForm.username");
//        }
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
//        if (user.getPassword().length() < 6 || user.getPassword().length() > 32 ) {
//            errors.rejectValue("password", "Size.userForm.password");
//        }
//        if (!user.getConfirmPassword().equals(user.getPassword())) {
//            errors.rejectValue("confirmPassword", "Different.userForm.password");
//        }
//    }
//}
