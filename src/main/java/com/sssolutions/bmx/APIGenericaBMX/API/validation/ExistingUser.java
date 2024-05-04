package com.sssolutions.bmx.APIGenericaBMX.API.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ExistingUserValidator.class)
public @interface ExistingUser {
	
	String message() default "Usuario existe, ingrese otro nombre de usuario.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
