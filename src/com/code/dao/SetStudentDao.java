package com.code.dao;

import com.code.exception.DaoException;
import com.code.exception.ProjectException;
import com.code.myEnum.DaoExceptionStatus;
import com.code.pojo.Student;

import java.util.*;

import static com.code.utils.ProjectUtils.updateStudent;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 17:22
 * @email 3349495429@qq.com
 * @className com.code.dao.SetStudentDao
 * @description:
 */
public class SetStudentDao implements BaseStudentDao{
    private static final Set<Student> studentSet = new TreeSet<>();
    static {
        Random random = new Random();
        for (int i = 0; i <10; i++) {
            studentSet.add( new Student(random.nextInt(100000),random.nextInt(1232)+"",random.nextInt(100),random.nextInt(2),"ds"));
        }
        studentSet.add( new Student(110,random.nextInt(1232)+"",random.nextInt(100),random.nextInt(2),"ds"));

    }
    @Override
    public List<Student> findAllStudents() {
        return new ArrayList<>(studentSet);
    }

    @Override
    public Student getStudentById(int id) throws DaoException {
        Iterator<Student> iterator = studentSet.iterator();
        Student student = null;
        boolean isFund = false;
        while(iterator.hasNext()){
            student = iterator.next();
            if(student.getId()==id){
                isFund = true;
                break;
            }
        }
        if(!isFund) throw DaoException.dataNotFund();
        return student;
    }

    @Override
    public int removeStudentById(int id) throws DaoException {
        Iterator<Student> iterator = studentSet.iterator();
        boolean isGetIt= false;
        while(iterator.hasNext()){
            Student student  = iterator.next();
            if(student.getId()==id){
                isGetIt = true;
                iterator.remove();
                break;
            }
        }
        if(!isGetIt) throw DaoException.dataNotFund();
        return 1;
    }

    @Override
    public int updateStudentById(Student student) throws ProjectException {
        Iterator<Student> iterator = studentSet.iterator();
        boolean isGetIt= false;
        while(iterator.hasNext()){
            Student student1 = iterator.next();
            if(student1.getId()==student.getId()){
                updateStudent(student1,student);
                isGetIt = true;
                break;
            }
        }
        if (!isGetIt) throw DaoException.dataNotFund();
        return 0;
    }


    @Override
    public int addStudent(Student student) throws DaoException {
        Iterator<Student> iterator = studentSet.iterator();
        boolean isGetIt= false;
        while(iterator.hasNext()){
            Student student1 = iterator.next();
            if(student1.getId()==student.getId()){
                isGetIt = true;
                break;
            }
        }
        if (isGetIt) throw DaoException.dataHasExist();
        studentSet.add(student);
        return 1;
    }
}
