package it.uniroma3.siw.progettoSIW.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AlbumValidator implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return AlbumForm.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "fotografoId", "required");

	}
}


