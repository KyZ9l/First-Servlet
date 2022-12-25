package com.dmdev.http.validator;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entyty.Gender;
import com.dmdev.http.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto>{

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    private CreateUserValidator() {
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();

        if(!LocalDateFormatter.isValid(object.getBirthday()))
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));

        if(object.getGender() == null || Gender.valueOf(object.getGender())==null)
        {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }

        return validationResult;
    }
}
