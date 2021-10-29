package com.iconagency.quotes.validations;

import com.iconagency.quotes.annotations.PasswordMatches;
import com.iconagency.quotes.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest userSignupRequest = (SignupRequest)obj;
        return userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword());
    }

}
