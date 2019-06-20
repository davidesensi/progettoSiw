package it.uniroma3.siw.progettoSIW.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component 
public class FotoValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return FotoForm.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "url", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "albumId", "required");


	}
}
