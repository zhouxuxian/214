package com.zxx.dao;

import com.zxx.domain.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TeacherDao {
    @Select("select * from teacherInfo")
    public List<Teacher> findAll();

    @Insert("insert into teacherInfo(id,name,classId) values (#{id},#{name},#{classId})")
    public void insert(Teacher teacher);
    @Delete("delete from teacherInfo where id = #{id}")
    public void delete(int id);

    @Update("update teacherInfo set name = #{name},classId=#{classId} where id= #{id}")
    public void update(Teacher teacher);
    @Select("select * from teacherInfo where id = #{id}")
    public Teacher findById(int id);
}
