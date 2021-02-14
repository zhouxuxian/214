package com.zxx.dao;

import com.zxx.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentDao {
    @Select("select * from stuInfo")
    public List<Student> findAll();

    @Insert("insert into stuInfo(id,name,sex,classId) values (#{id},#{name},#{sex},#{classId})")
    public void insert(Student student);

    @Delete("delete from stuInfo where id = #{id}")
    public void delete(int id);

    @Update("update stuInfo set name = #{name},sex=#{sex},classId=#{classId} where id= #{id}")
    public void update(Student student);

    @Select("select * from stuInfo where id = #{id}")
    public Student findById(int id);


}
