package pl.kurs.advanced.jpa.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        createTableForStudent();
        Student_ student1=new Student_(1,"Paweł");
        Student_ student2=new Student_(2,"Jacek");

        insertStudent(student1);
        insertStudent(student2);

        List<Student_> students = getStudent();

        students.forEach(System.out::println);

        
    }

    private static Statement createConn() throws SQLException, ClassNotFoundException {

        Connection connection=H2Configuration.getDBConnetion();
        Statement statement = connection.createStatement();

        return statement;

    }


    private static void createTableForStudent() throws SQLException, ClassNotFoundException {

        Connection connection=H2Configuration.getDBConnetion();
        Statement statement = connection.createStatement();

        String sql = "create table student(id int primary key,name varchar(255))";
        statement.execute(sql);
        connection.commit();

    }
    private static void insertStudent(Student_ student) throws SQLException, ClassNotFoundException {

        Connection connection=H2Configuration.getDBConnetion();
        Statement statement = connection.createStatement();
        String sql = "insert into student values( "+student.getId()+",'"+student.getName()+"' )";
        statement.execute(sql);
        connection.commit();

    }


    private static List<Student_> getStudent() throws SQLException, ClassNotFoundException {

        List<Student_> students = new ArrayList<>();


        Connection connection=H2Configuration.getDBConnetion();
        Statement statement = connection.createStatement();
        String sql = "select * from student";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next())
        {
            int id = resultSet.getInt("Id");
            String name= resultSet.getString("name");

            students.add(new Student_(id,name));

        }


        return students;

    }


}
