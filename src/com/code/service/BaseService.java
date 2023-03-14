package com.code.service;

import com.code.exception.DaoException;
import com.code.exception.ProjectException;
import com.code.pojo.Student;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 17:05
 * @email 3349495429@qq.com
 * @className com.code.service.StudentService
 * @description:
 */
public interface BaseService<T> {
    List<T> getAll();
    T getById(int id) throws DaoException;
    int add(T t) throws DaoException;
    int delete(int id) throws DaoException;
    int update(T t) throws ProjectException;
}
