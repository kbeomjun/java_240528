package db.day02.ex01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import db.day02.ex01.vo.StudentVO;

public interface StudentDAO {

	void insertStudent(
			@Param("grade")int grade, 
			@Param("classNum")int classNum, 
			@Param("num")int num, 
			@Param("name")String name);
	
	void deleteStudent(
			@Param("grade")int grade, 
			@Param("classNum")int classNum, 
			@Param("num")int num);

	ArrayList<StudentVO> selectStudentList();

	ArrayList<StudentVO> selectStudentList2();

	StudentVO selectStudentByKey(@Param("studentNum")int key);
	
}