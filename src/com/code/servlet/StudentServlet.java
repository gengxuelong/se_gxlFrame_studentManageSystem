package com.code.servlet;

import com.code.exception.DaoException;
import com.code.pojo.Student;
import com.code.service.StudentService;
import com.code.utils.ProjectUtils;
import com.httpserver.anno.GxlWebServlet;
import com.httpserver.servlet.GxlHttpServlet;
import com.httpserver.twoHand.GxlHttpRequest;
import com.httpserver.twoHand.GxlHttpResponse;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/4 17:36
 * @email 3349495429@qq.com
 * @className com.code.servlet.StudentServlet
 * @description:
 */
@GxlWebServlet(urlPatterns = "/servlet/user")
public class StudentServlet implements GxlHttpServlet {
    private final StudentService studentService = new StudentService();
    @Override
    public void service(GxlHttpRequest gxlHttpRequest, GxlHttpResponse gxlHttpResponse) {
	String method = gxlHttpRequest.getParams().get("method");
	System.out.println(method);
	System.out.println(gxlHttpRequest.getParams());
	if("showAll".equals(method)){
	    this.showAll(gxlHttpRequest,gxlHttpResponse);
	}else if("findOne".equals(method)){
	    this.findOne(gxlHttpRequest,gxlHttpResponse);
	}else if("add".equals(method)){
	    this.addOne(gxlHttpRequest,gxlHttpResponse);
	}else if("update".equals(method)){
	    this.updateOne(gxlHttpRequest,gxlHttpResponse);
	}else if("delete".equals(method)){
	    this.deleteOne(gxlHttpRequest,gxlHttpResponse);
	}
	else{
	    gxlHttpResponse.write("<h2>sorry , this method is developing now and it can not work still</h2>");
	}
    }

    private void deleteOne(GxlHttpRequest gxlHttpRequest, GxlHttpResponse gxlHttpResponse) {
	int id = Integer.parseInt(gxlHttpRequest.getParams().get("id"));
	try {
	    int res = studentService.delete(id);
	    if(res>0){
	        gxlHttpResponse.write("删除成功");
	    }
	} catch (DaoException e) {
	    gxlHttpResponse.write(e.getMessage());
	    e.printStackTrace();
	}
    }

    private void updateOne(GxlHttpRequest gxlHttpRequest, GxlHttpResponse gxlHttpResponse) {
	gxlHttpResponse.write("更新学生,具体懒得做了");
    }

    private void addOne(GxlHttpRequest gxlHttpRequest, GxlHttpResponse gxlHttpResponse) {
	gxlHttpResponse.write("添加学生,具体懒得做了");
    }

    private void findOne(GxlHttpRequest gxlHttpRequest, GxlHttpResponse gxlHttpResponse) {
	String id = gxlHttpRequest.getParams().get("id");
	if(ProjectUtils.isEmpty(id)){
	    gxlHttpResponse.write("<h1>error: 请您输入要查询学生的id</h1>");
	}else{
	    try {
		Student student = studentService.getById(Integer.parseInt(id));
		List<Student> student1 = List.of(student);
		gxlHttpResponse.write(this.writeStudent(student1));
	    } catch (Exception e) {
	        gxlHttpResponse.write(e.getMessage());
		e.printStackTrace();
	    }
	}
    }

    private void showAll(GxlHttpRequest gxlHttpRequest, GxlHttpResponse gxlHttpResponse) {
	List<Student>  studentList = this.studentService.getAll();
	gxlHttpResponse.write(this.writeStudent(studentList));
    }

    private String writeStudent(List<Student> studentList){
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("id\t\tname\t\tage\t\tsex\t\taddress\n");
	for (Student student : studentList) {
	    stringBuilder.append(student.getId()).append("\t\t").append(student.getName()).append("\t\t").append(student.getAge()).append("\t\t").append(student.getSex()).append("\t\t").append(student.getAddress()).append("\n");
	}
	return stringBuilder.toString();
    }
}
