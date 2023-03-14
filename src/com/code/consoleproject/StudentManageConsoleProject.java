package com.code.consoleproject;

import com.code.exception.DaoException;
import com.code.exception.ProjectException;
import com.code.pojo.Student;
import com.code.service.BaseService;
import com.code.service.StudentService;
import com.code.utils.ProjectUtils;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 17:13
 * @email 3349495429@qq.com
 * @className com.code.controller.StudentController
 * @description:
 */
public class StudentManageConsoleProject {
    private  final BaseService<Student> studentService = new StudentService();

    public static void main(String[] args) {
        StudentManageConsoleProject studentManageConsoleProject = new StudentManageConsoleProject();
        studentManageConsoleProject.startServer();
    }
    public  void startServer() {
        ProjectUtils.printWelcome();
        outer:
        while(true){
            int command = getACommandFromConsole();
            switch (command){
                case 1:
                    handleShowAll();
                    break;
                case 2:
                    handleShowOne();
                    break;
                case 3:
                    handleAdd();
                    break;
                case 4:
                    handleDelete();
                    break;
                case 5:
                    handleUpdate();
                    break;
                case 6:
                    handleExist();
                    break outer;
                case -1:
                default:
                    handleWrongCommand();
            }

        }
    }

    private  void handleShowAll() {
        System.out.println("开始查询");
        List<Student> studentList = studentService.getAll();
        System.out.println("查询结束，查询数据展示如下:");
        ProjectUtils.showStudentList(studentList);
    }

    private  void handleShowOne() {
        int id = ProjectUtils.getIntFromConsole("请输入您要查询学生的id:");
        try {
            System.out.println("开始查询");
            Student student = studentService.getById(id);
            System.out.println("查询成功，数据展示入下：");
            ProjectUtils.showStudent(student);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            System.out.println("查询失败");
        }
    }

    private  void handleAdd() {
        int id = ProjectUtils.getIntFromConsole("请输入要添加学生的学号，要求整数");
        String name = ProjectUtils.getStringFromConsole("请输入添加学生的姓名");
        int sex = ProjectUtils.getSexFromConsole("请输入添加学生的性别,要求整数，1代表男，0代表女");
        int age = ProjectUtils.getIntFromConsole("请输入添加学生的年龄，要求整数");
        String address = ProjectUtils.getStringFromConsole("请输入添加学生的地址");
        Student student = new Student(id,name,age,sex,address);
        try {
            System.out.println("开始添加");
            studentService.add(student);
            System.out.println("添加完成");
        } catch (ProjectException e) {
            System.out.println(e.getMessage());
            System.out.println("添加失败");
        }

    }

    private  void handleDelete() {
        int id = ProjectUtils.getIntFromConsole("请输入要删除学生的学号:");
        try {
            System.out.println("开始删除");
            studentService.delete(id);
            System.out.println("删除成功");
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            System.out.println("删除失败");
        }
    }

    private  void handleUpdate() {
        int id = ProjectUtils.getIntFromConsole("请输入要修改学生的学号，要求整数");
        String name = ProjectUtils.getStringFromConsole("请输入修改后学生的姓名");
        int sex = ProjectUtils.getIntFromConsole("请输入修改后学生的性别,要求整数，1代表男，0代表女");
        int age = ProjectUtils.getIntFromConsole("请输入修改后学生的年龄，要求整数");
        String address = ProjectUtils.getStringFromConsole("请输入修改后学生的地址");
        Student student = new Student(id,name,age,sex,address);
        try {
            System.out.println("开始更新");
            studentService.update(student);
            System.out.println("更新成功");
        } catch (ProjectException e) {
            System.out.println(e.getMessage());
            System.out.println("更新失败");
        }

    }

    private  void handleExist() {
        System.out.println("bye! 欢迎下次使用。");
    }

    private  void handleWrongCommand() {
        System.out.println("您输入的命令无效，请重新选择命令输入。");
    }

    private  int getACommandFromConsole() {
        ProjectUtils.printMenu();
        String res = ProjectUtils.getScanner().next();
        int resI;
        try{
            resI = Integer.parseInt(res);
        }catch (NumberFormatException numberFormatException){
            return -1;
        }
        if(resI<1||resI>6){
            return -1;
        }
        return resI;
    }

}
