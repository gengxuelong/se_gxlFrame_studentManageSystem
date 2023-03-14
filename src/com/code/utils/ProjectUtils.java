package com.code.utils;

import com.code.exception.ProjectException;
import com.code.pojo.Student;

import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/2 17:14
 * @email 3349495429@qq.com
 * @className com.code.utils.ProjectUtils
 * @description:
 */
public class ProjectUtils {

    private static final Scanner scanner = new Scanner(System.in);
    public static Scanner getScanner(){return scanner;}
    public static void updateStudent2(Student targetStudent, Student dataFromStudent){
        targetStudent.setName(dataFromStudent.getName());
        targetStudent.setAddress(dataFromStudent.getAddress());
        targetStudent.setAge(dataFromStudent.getAge());
        targetStudent.setSex(dataFromStudent.getSex());
    }
    public static void updateStudent(Student target,Student dataFrom) throws ProjectException {
        copyObject(target,dataFrom,Student.class);
    }
    public static void copyObject(Object target,Object dataFrom,Class clazz) throws ProjectException {
         if(!target.getClass().isAssignableFrom(clazz)||!dataFrom.getClass().isAssignableFrom(clazz)){
             throw new ProjectException("类型转变失败，请在使用copyObject函数的时候传入正确类型的参数");
         }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                declaredField.setAccessible(true);
                Object o = declaredField.get(dataFrom);
                declaredField.set(target,o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
    public static void printWelcome(){
        System.out.println("欢迎使用雪龙公司的学生管理系统，祝您使用愉快");
    }
    public static void printMenu(){
        System.out.println("请选择要执行的命令：");
        System.out.println("1：展示所有学生");
        System.out.println("2：查询一个学生");
        System.out.println("3：添加一个学生");
        System.out.println("4：删除一个学生");
        System.out.println("5：修改一个学生");
        System.out.println("6：exit");
    }
    public static String getStringFromConsole(String description){
        System.out.println(description);
        String res;
        while (true){
            res = scanner.next();
            if(isEmpty(res)){
                System.out.println("输入不能为空，请重新填写:");
            }else{
                break;
            }
        }
        return res;
    }
    public static int getIntFromConsole(String description){
        System.out.println(description);
        int number;
        while(true){
            try{
                number = scanner.nextInt();
                break;
            }catch (InputMismatchException ignored){
                String next = scanner.next();
                System.out.println("所输入数据"+next+"的类型不是整数，类型不正确，请重新输入并输入整数:");
            }
        }
        return number;
    }
    public static int getSexFromConsole(String s) {
        int sex = getIntFromConsole(s);
        while (true){
            if(sex!=0&&sex!=1){
                sex = getIntFromConsole("性别只能是0或1，请重新输入符合要求的数");
            }else{
                break;
            }
        }
        return sex;
    }
    public static boolean isEmpty(String string){
        if(string ==null) return true;
        char[] chars = string.toCharArray();
        boolean isEmpty = true;
        for (char aChar : chars) {
            if (aChar != ' ') {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }
    public static void showStudent(Student student) {
        printStudentHeader();
        printStudentRow(student);

    }
    public static void showStudentList(List<Student> studentList) {
        printStudentHeader();
        for (Student student : studentList) {
           printStudentRow(student);
        }
    }
    private static void printStudentHeader(){
        System.out.printf("%-10s","id");
        System.out.printf("%-10s","name");
        System.out.printf("%-10s","sex");
        System.out.printf("%-10s","age");
        System.out.printf("%-10s","address");
        System.out.println();
    }
    private static void printStudentRow(Student student){
        System.out.printf("%-10s",student.getId());
        System.out.printf("%-10s",student.getName());
        System.out.printf("%-10s",student.getSex());
        System.out.printf("%-10s",student.getAge());
        System.out.printf("%-10s",student.getAddress());
        System.out.println();
    }


}
