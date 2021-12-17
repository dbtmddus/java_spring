package com.test.mybatis;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertStudent(StudentModel stdmodel) {
		// TODO Auto-generated method stub

		StudentDAO mapper=sqlSession.getMapper(StudentDAO.class);
		int n = mapper.insertStudent(stdmodel);
		return n;
	}
    
    @Override
    public ArrayList<StudentModel> selectStudent()
    {
        StudentDAO mapper=sqlSession.getMapper(StudentDAO.class);
        ArrayList<StudentModel> arr = mapper.selectStudent();
        return arr;
    }
    
    @Override
    public ArrayList<StudentModel> selectStudentOrder()
    {
        StudentDAO mapper=sqlSession.getMapper(StudentDAO.class);
        ArrayList<StudentModel> arr = mapper.selectStudentOrder();
        return arr;
    }   

    @Override
    public ArrayList<StudentModel> selectStudentWhere(String name)
    {
        StudentDAO mapper=sqlSession.getMapper(StudentDAO.class);
        ArrayList<StudentModel> arr = mapper.selectStudentWhere(name);
        return arr;
    }   
    
    @Override
    public ArrayList<StudentModel> selectStudentOr( HashMap<String,Object> arg )
    {
        StudentDAO mapper=sqlSession.getMapper(StudentDAO.class);
        ArrayList<StudentModel> arr = mapper.selectStudentOr(arg);
        return arr;        
    }
    
}
