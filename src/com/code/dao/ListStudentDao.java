package com.code.dao;

import com.code.exception.DaoException;
import com.code.exception.ProjectException;
import com.code.myEnum.DaoExceptionStatus;
import com.code.pojo.Student;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.code.utils.ProjectUtils.updateStudent;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 16:12
 * @email 3349495429@qq.com
 * @className com.code.dao.ListStudentDao
 * @description:
 */
public class ListStudentDao implements BaseStudentDao{

    public static final List<Student> studentList = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            Student student = new Student(202030+i,"gxl"+i,21-i,1,"henan,kaifeng");
            studentList.add(student);
        }

    }

    @Override
    public List<Student> findAllStudents() {
        return new ArrayList<>(studentList);
    }

    @Override
    public Student getStudentById(int id) throws DaoException {

        int index = this.getIndexById(id);
        if(index == -1) throw DaoException.dataNotFund();
        Student student = studentList.get(index);
            if(student==null) throw DaoException.dataSystemError("查询失败");
        return student;
    }

    @Override
    public int removeStudentById(int id) throws DaoException {

        int index = this.getIndexById(id);
        if(index == -1) throw DaoException.dataNotFund();
        Student remove = studentList.remove(index);
        if(remove==null) throw DaoException.dataSystemError("删除失败");
        return 1;
    }

    @Override
    public int updateStudentById(Student student) throws ProjectException {
        int index = this.getIndexById(student.getId());
        if(index == -1) throw DaoException.dataNotFund();
        Student oldStudent = studentList.get(index);
        updateStudent(oldStudent,student);
        return 1;
    }

    @Override
    public int addStudent(Student student) throws DaoException {
        int index = this.getIndexById(student.getId());
        if(index!=-1) throw DaoException.dataHasExist();
        boolean f = studentList.add(student);
        return 0;
    }

    private int getIndexById(int id){
        int index = -1;
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if(student.getId()==id){
                index = i;
            }
        }
        return index;
    }
}
