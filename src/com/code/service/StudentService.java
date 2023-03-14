package com.code.service;

import com.code.dao.BaseStudentDao;
import com.code.dao.ListStudentDao;
import com.code.dao.SetStudentDao;
import com.code.exception.DaoException;
import com.code.exception.ProjectException;
import com.code.pojo.Student;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 17:09
 * @email 3349495429@qq.com
 * @className com.code.service.StudentService
 * @description:
 */
public class StudentService implements BaseService<Student> {

    private final BaseStudentDao studentDao = new SetStudentDao();

    @Override
    public List<Student> getAll() {
        return studentDao.findAllStudents();
    }

    @Override
    public Student getById(int id) throws DaoException {
        return studentDao.getStudentById(id);
    }

    @Override
    public int add(Student student) throws DaoException {
        return studentDao.addStudent(student);
    }

    @Override
    public int delete(int id) throws DaoException {
        return studentDao.removeStudentById(id);
    }

    @Override
    public int update(Student student) throws ProjectException {
        return studentDao.updateStudentById(student);
    }
}
