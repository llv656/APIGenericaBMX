package com.sssolutions.bmx.APIGenericaBMX.API.validation;

import com.sssolutions.bmx.APIGenericaBMX.API.aspects.PreProcessingSetupAspect;
import com.sssolutions.bmx.APIGenericaBMX.API.model.RequestAddUserExampleModel;
import com.sssolutions.bmx.APIGenericaBMX.BD.dao.IUserExampleRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExistingUserValidator implements ConstraintValidator<ExistingUser, RequestAddUserExampleModel>{
	
	private IUserExampleRepository exampleRepository;

	@Override
	public boolean isValid(RequestAddUserExampleModel value, ConstraintValidatorContext context) {
		value.sanitizeFields();
		if (!exampleRepository.validateUserExist(value.getNombre(), PreProcessingSetupAspect.asyncCredentials.get()).isValid()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Usuario existe, ingrese otro nombre de usuario.")
					.addPropertyNode("nombre")
					.addConstraintViolation();
			return false;
		}
		
		return true;
	}

}
