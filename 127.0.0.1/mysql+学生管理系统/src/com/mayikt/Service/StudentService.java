package com.mayikt.Service;

import com.mayikt.dao.StudentDao;
import com.mayikt.entity.StudentEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentService {
    private StudentDao studentDao = new StudentDao();
    
    public ArrayList<StudentEntity> allStudent() throws SQLException, ClassNotFoundException {
        ArrayList<StudentEntity> studentEntities = studentDao.allStudent();
        return studentEntities;
    }

    public StudentEntity getStudentById(Long id) throws SQLException, ClassNotFoundException {
        return studentDao.getByidStudent(id);
    }

    public int insertStudent(StudentEntity student) throws SQLException, ClassNotFoundException {
        return studentDao.Insertstudent(student);
    }

    public int updateStudent(StudentEntity student) throws SQLException, ClassNotFoundException {
        return studentDao.Updatestudent(student);
    }

    public int deleteStudent(Long id) throws SQLException, ClassNotFoundException {
        return studentDao.deletestudent(id);
    }

}
