package com.code.dao;

import com.code.exception.DaoException;
import com.code.exception.ProjectException;
import com.code.pojo.Student;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 16:10
 * @email 3349495429@qq.com
 * @className com.code.dao.BaseStudentDao
 * @description:
 */
public interface BaseStudentDao {
    List<Student> findAllStudents();
    Student getStudentById(int id) throws DaoException;
    int removeStudentById(int id) throws DaoException;
    int updateStudentById(Student student) throws ProjectException;
    int addStudent(Student student) throws DaoException;

}
