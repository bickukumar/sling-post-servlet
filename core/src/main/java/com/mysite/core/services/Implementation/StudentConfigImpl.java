package com.mysite.core.services.Implementation;

import com.mysite.core.services.Student;
import com.mysite.core.services.StudentConfig;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component(service = StudentConfig.class)
public class StudentConfigImpl implements StudentConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static List<Student> listOfStudents = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        listOfStudents.add(student);
    }

    @Override
    public void deleteStudent(int index) {
        LOGGER.info("Printing the list of students" + listOfStudents);
        listOfStudents.remove(index);
        LOGGER.info("Printed" + listOfStudents);

    }

    @Override
    public boolean isStudentPassed(Student student) {
        if(student.getMarks_obtained() >= ClassSizeAndMarksImpl.marks)
            return true;
        return false;
    }

    @Override
    public Student getStudent(Student student) {
        return student;
    }

    @Override
    public List<Student> getAllStudents() {

        return listOfStudents;
    }
}
