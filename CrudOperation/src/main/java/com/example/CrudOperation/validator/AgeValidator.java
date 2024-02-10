package com.example.CrudOperation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgeValidator implements ConstraintValidator<Age, Integer> {

    enum gender{male,female}
    Logger logger = LoggerFactory.getLogger(AgeValidator.class);

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        if (age <= 30) {        //age is less than or equal 30
            logger.info("My age is " + age + "and i am a "+gender.male);
            return true;
        } else if (age >=31 && age<= 45) {        //age is greater than or equal 31 and is less than or equal 45
            logger.info("My age is " + age + "and i am a "+gender.female);
            return true;
        }
        else {              //age is greater than 45.
            logger.error("My age is " + age + "and this is invalid age");
            return false;
        }
    }
}