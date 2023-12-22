package org.llin.twelvequotes.controller.util;

import org.llin.twelvequotes.model.QuoteDetailRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class QuoteDetailRequestValidator implements Validator {
	
    @Override
    public boolean supports(Class<?> clazz) {
        return QuoteDetailRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuoteDetailRequest request = (QuoteDetailRequest) target;
        request.setTimeStamps();
        
        long ts1 =  request.getPeriodTimeStamp1();
        long ts2 =  request.getPeriodTimeStamp2();
        
        // Custom validation logic
        if (ts1 <= 0) {
            errors.rejectValue("From Date", "error.date.invalid");
        }
        
        if (ts2 <= 0) {
            errors.rejectValue("To Date", "error.date.invalid");
        }
        
        if (ts2 < ts1) {
        	errors.reject("error.dates.invalid");
        }
        
    }
}