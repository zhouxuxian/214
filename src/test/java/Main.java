import com.zxx.dao.StudentDao;
import com.zxx.dao.TeacherDao;
import com.zxx.domain.Student;
import com.zxx.domain.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static StudentDao studentDao = null;
    public static TeacherDao teacherDao = null;
    public static SqlSessionFactory factory = null;
    public static SqlSession sqlSession = null;

    static {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            factory = new SqlSessionFactoryBuilder().build(in);
            sqlSession = factory.openSession(false);

            studentDao = sqlSession.getMapper(StudentDao.class);
            teacherDao = sqlSession.getMapper(TeacherDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test1() {
        List<Student> allstu = studentDao.findAll();
        allstu.forEach(System.out::println);
        System.out.println("-----------------------");
        List<Teacher> allteacher = teacherDao.findAll();
        allteacher.forEach(System.out::println);
    }

    @Test
    public void test2() {
        Student student = new Student(6, "abcdf", "女", 3);
        if (studentDao.findById(student.getId()) != null) {
            System.out.println("已经有这个学生了");

        } else {
            studentDao.insert(student);
        }
        Teacher teacher = new Teacher(4, "hfy", 3);
        if (teacherDao.findById(teacher.getId()) != null) {
            System.out.println("已经有这个老师了");
        } else {

            teacherDao.insert(teacher);
        }
        sqlSession.commit();

    }

    @Test
    public void test3() {

        studentDao.delete(6);

        teacherDao.delete(4);
        sqlSession.commit();
    }

    @Test
    public void test4() {
        Student newStudent = new Student(6, "efgh", "女", 4);
        studentDao.update(newStudent);
        if (newStudent.getId()==6){
            System.out.println("这个学生不能改");
            sqlSession.rollback();
        }else {
            sqlSession.commit();
        }
        Teacher newTeacher = new Teacher(4, "abc", 4);
        teacherDao.update(newTeacher);
        sqlSession.commit();
    }


}
