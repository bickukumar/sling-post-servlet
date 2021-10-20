package com.mysite.core.services.Implementation;

import com.mysite.core.services.ClassSizeAndMarks;
import com.mysite.core.services.Configuration.ClassConfigurationService;
import com.mysite.core.services.Student;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component(service = ClassSizeAndMarks.class)
@Designate(ocd = ClassConfigurationService.class)
public class ClassSizeAndMarksImpl implements ClassSizeAndMarks {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static int allowedSize;
    public static int marks;

    @Activate
    public void activate(ClassConfigurationService conf){
        marks = conf.requiredMarks();
        allowedSize = conf.noOfAllowedStudents();
        LOGGER.info("Activated");
    }

    @Modified
    public void modify(ClassConfigurationService conf){
        LOGGER.info("Modified");
    }

    @Deactivate
    public void deactivate(ClassConfigurationService conf){
        LOGGER.info("Deactivated");
    }

    @Override
    public boolean isClassSizeLimited(List<Student> listOfStudents) {

        if(StudentConfigImpl.listOfStudents.size() < allowedSize)
            return true;
        return false;
    }

    @Override
    public int getdesiredMarks() {
        return marks;
    }
}
