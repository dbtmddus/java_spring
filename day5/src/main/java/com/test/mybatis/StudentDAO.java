package com.test.mybatis;
import java.util.ArrayList;
import java.util.HashMap;

public interface StudentDAO {
    public int insertStudent(StudentModel stdmodel);
    public ArrayList<StudentModel> selectStudent();
    public ArrayList<StudentModel> selectStudentOrder();
    public ArrayList<StudentModel> selectStudentWhere(String name);
    public ArrayList<StudentModel> selectStudentOr( HashMap<String,Object> arg );
}
