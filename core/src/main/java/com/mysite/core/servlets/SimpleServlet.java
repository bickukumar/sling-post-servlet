/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mysite.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.mysite.core.services.Implementation.ClassSizeAndMarksImpl;
import com.mysite.core.services.Implementation.StudentConfigImpl;
import com.mysite.core.services.Student;
import com.mysite.core.services.StudentConfig;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysite.core.services.ClassSizeAndMarks;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = { Servlet.class })
@SlingServletPaths(value = "/bin/myservlet")
public class SimpleServlet extends SlingSafeMethodsServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final long serialVersionUID = 1L;

    @Reference
    ClassSizeAndMarks classSizeAndMarks;

    @Reference
    StudentConfig studentConfig;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("servlet called");

        Student student1 = new Student("Joel", 101, 25, 60.5f);
        Student student2 = new Student("Dora", 102, 24, 80.5f);
        Student student3 = new Student("Julie", 103, 23, 30.5f);

        int ch = Integer.parseInt(req.getParameter("a"));

        resp.getWriter().write("1 2 3 for adding students\n 4 for deleting student: Dora\n 5 for checking if student: Julie is passed\n 6 for getting student: Julie details\n 7 for getting all students\n");
        resp.getWriter().write("\n\n");

        switch (ch){
            case 1: studentConfig.addStudent(student1);
            resp.getWriter().write("Adding student: Joel\n");
            break;
            case 2: studentConfig.addStudent(student2);
            resp.getWriter().write("Adding student: Dora\n");
            break;
            case 3: studentConfig.addStudent(student3);
            resp.getWriter().write("Adding student: Julie\n");
            break;
            case 4: studentConfig.deleteStudent(1);
            resp.getWriter().write("Deleting student: Dora\n");
            break;
            case 5: boolean status = studentConfig.isStudentPassed(student3);
            resp.getWriter().write("Is Julie passed?: " + String.valueOf(status));
            break;
            case 6: Student stu = studentConfig.getStudent(student3);
            resp.getWriter().write(String.valueOf(stu));
            break;
            case 7: resp.getWriter().write(String.valueOf(StudentConfigImpl.listOfStudents));
            break;
            default: resp.getWriter().write("\nWaiting for the response...");
        }

        LOGGER.info("Switch case ran.");

        resp.getWriter().write("\n\nThe required marks are : " + classSizeAndMarks.getdesiredMarks());
        resp.getWriter().write("\nIs class Size limited? : " + classSizeAndMarks.isClassSizeLimited(StudentConfigImpl.listOfStudents));

    }

}
