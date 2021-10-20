package com.mysite.core.services.Configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import java.util.Arrays;

@ObjectClassDefinition(name = "My Student Configuration")
public @interface ClassConfigurationService {

    @AttributeDefinition(name = "Required marks and allowed class size", required = true) //no need for required = true for now
    int noOfAllowedStudents() default 60;
    int requiredMarks() default 33;


}
