package com.company.project.service;
import com.company.project.model.Student;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2020/05/05.
 */
public interface StudentService extends Service<Student> {

    void saveAll(Student student);
}
